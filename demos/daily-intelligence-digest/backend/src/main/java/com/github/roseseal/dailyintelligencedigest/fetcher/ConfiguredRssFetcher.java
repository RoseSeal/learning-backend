package com.github.roseseal.dailyintelligencedigest.fetcher;

import com.github.roseseal.dailyintelligencedigest.config.DailyDigestProperties;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ConfiguredRssFetcher implements RssFetcher {

	private static final Logger log = LoggerFactory.getLogger(ConfiguredRssFetcher.class);

	private static final Pattern HTML_TAG_PATTERN = Pattern.compile("(?s)<[^>]*>");

	private final DailyDigestProperties properties;
	private final HttpClient httpClient;

	public ConfiguredRssFetcher(DailyDigestProperties properties) {
		this.properties = properties;
		this.httpClient = HttpClient.newBuilder()
				.connectTimeout(properties.getConnectTimeout())
				.followRedirects(HttpClient.Redirect.NORMAL)
				.build();
	}

	@Override
	public List<RawItem> fetch() {
		List<RawItem> items = new ArrayList<>();
		for (DailyDigestProperties.RssSource source : properties.getRssSources()) {
			if (source.getUrl() == null || source.getUrl().isBlank()) {
				log.warn("Skipping RSS source with blank url");
				continue;
			}
			try {
				items.addAll(fetchSource(source));
			}
			catch (IOException | InterruptedException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				}
				log.warn("Failed to fetch RSS source {} ({})", source.getName(), source.getUrl(), e);
			}
		}
		return items;
	}

	private List<RawItem> fetchSource(DailyDigestProperties.RssSource source) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(source.getUrl()))
				.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
				.timeout(properties.getReadTimeout())
				.GET()
				.build();
		HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
		try (InputStream responseBody = response.body()) {
			if (response.statusCode() >= 400) {
				throw new IOException("RSS fetch returned status " + response.statusCode());
			}
			List<RawItem> items = parseFeed(source.getName(), responseBody, source.getMaxItems());
			log.info("Fetched {} items from {}", items.size(), source.getName());
			return items;
		}
	}

	List<RawItem> parseFeed(String sourceName, InputStream responseBody, int maxItems) throws IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		configureXmlFactory(factory);
		Document document;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(responseBody);
		}
		catch (ParserConfigurationException | SAXException e) {
			throw new IOException("Failed to parse feed for source " + sourceName, e);
		}

		Element root = document.getDocumentElement();
		String rootName = nodeName(root);
		if ("rss".equalsIgnoreCase(rootName)) {
			return parseRssItems(sourceName, root, maxItems);
		}
		if ("feed".equalsIgnoreCase(rootName)) {
			return parseAtomEntries(sourceName, root, maxItems);
		}
		throw new IOException("Unsupported feed root element: " + rootName);
	}

	private void configureXmlFactory(DocumentBuilderFactory factory) {
		factory.setNamespaceAware(true);
		trySetFeature(factory, "http://apache.org/xml/features/disallow-doctype-decl", true);
		trySetFeature(factory, "http://xml.org/sax/features/external-general-entities", false);
		trySetFeature(factory, "http://xml.org/sax/features/external-parameter-entities", false);
		trySetFeature(factory, XMLConstants.FEATURE_SECURE_PROCESSING, true);
		factory.setXIncludeAware(false);
		factory.setExpandEntityReferences(false);
	}

	private void trySetFeature(DocumentBuilderFactory factory, String feature, boolean enabled) {
		try {
			factory.setFeature(feature, enabled);
		}
		catch (ParserConfigurationException ignored) {
			log.debug("XML parser does not support feature {}", feature);
		}
	}

	private List<RawItem> parseRssItems(String sourceName, Element root, int maxItems) {
		Element channel = firstChild(root, "channel");
		if (channel == null) {
			return List.of();
		}
		List<RawItem> items = new ArrayList<>();
			for (Element itemElement : childElements(channel, "item")) {
				items.add(buildRawItem(
						sourceName,
						text(itemElement, "guid"),
						text(itemElement, "link"),
						text(itemElement, "title"),
						firstNonBlank(
								text(itemElement, "description"),
								text(itemElement, "content:encoded"),
								text(itemElement, "encoded")
						),
						text(itemElement, "pubDate")
				));
			if (items.size() >= maxItems) {
				break;
			}
		}
		return items;
	}

	private List<RawItem> parseAtomEntries(String sourceName, Element root, int maxItems) {
		List<RawItem> items = new ArrayList<>();
		for (Element entryElement : childElements(root, "entry")) {
			items.add(buildRawItem(
					sourceName,
					text(entryElement, "id"),
					atomLink(entryElement),
					text(entryElement, "title"),
					firstNonBlank(text(entryElement, "summary"), text(entryElement, "content")),
					firstNonBlank(text(entryElement, "published"), text(entryElement, "updated"))
			));
			if (items.size() >= maxItems) {
				break;
			}
		}
		return items;
	}

	private RawItem buildRawItem(
			String sourceName,
			String explicitId,
			String link,
			String title,
			String content,
			String publishTime
	) {
		String normalizedTitle = sanitizeText(firstNonBlank(title, "Untitled"));
		String normalizedLink = firstNonBlank(link, "");
		String normalizedContent = sanitizeText(firstNonBlank(content, normalizedTitle));
		String rawId = firstNonBlank(explicitId, normalizedLink, normalizedTitle);
		String id = UUID.nameUUIDFromBytes((sourceName + "|" + rawId).getBytes(StandardCharsets.UTF_8)).toString();
		return new RawItem(
				id,
				sourceName,
				normalizedTitle,
				normalizedLink,
				normalizedContent,
				parsePublishTime(publishTime)
		);
	}

	private LocalDateTime parsePublishTime(String value) {
		if (value == null || value.isBlank()) {
			return LocalDateTime.now(ZoneOffset.UTC);
		}
		List<java.util.function.Function<String, LocalDateTime>> parsers = List.of(
				text -> ZonedDateTime.parse(text, DateTimeFormatter.RFC_1123_DATE_TIME)
						.withZoneSameInstant(ZoneOffset.UTC)
						.toLocalDateTime(),
				text -> OffsetDateTime.parse(text).withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime(),
				text -> Instant.parse(text).atOffset(ZoneOffset.UTC).toLocalDateTime(),
				LocalDateTime::parse
		);
		for (java.util.function.Function<String, LocalDateTime> parser : parsers) {
			try {
				return parser.apply(value.trim());
			}
			catch (DateTimeParseException ignored) {
				// Try the next parser.
			}
		}
		log.debug("Falling back to current time for unsupported publish time {}", value);
		return LocalDateTime.now(ZoneOffset.UTC);
	}

	private String atomLink(Element entryElement) {
		for (Element link : childElements(entryElement, "link")) {
			String rel = link.getAttribute("rel");
			if (rel.isBlank() || "alternate".equalsIgnoreCase(rel)) {
				return link.getAttribute("href");
			}
		}
		return "";
	}

	private Element firstChild(Element parent, String targetName) {
		for (Element child : childElements(parent, targetName)) {
			return child;
		}
		return null;
	}

	private List<Element> childElements(Element parent, String targetName) {
		List<Element> elements = new ArrayList<>();
		NodeList childNodes = parent.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (child instanceof Element element && targetName.equalsIgnoreCase(nodeName(element))) {
				elements.add(element);
			}
		}
		return elements;
	}

	private String text(Element parent, String targetName) {
		Element child = firstChild(parent, targetName);
		if (child == null) {
			return "";
		}
		return Objects.toString(child.getTextContent(), "");
	}

	private String nodeName(Node node) {
		return firstNonBlank(node.getLocalName(), node.getNodeName()).toLowerCase(Locale.ROOT);
	}

	private String sanitizeText(String value) {
		String withoutTags = HTML_TAG_PATTERN.matcher(value).replaceAll(" ");
		return withoutTags
				.replace("&nbsp;", " ")
				.replace("&amp;", "&")
				.replace("&lt;", "<")
				.replace("&gt;", ">")
				.replace("&quot;", "\"")
				.replace("&#39;", "'")
				.replaceAll("\\s+", " ")
				.trim();
	}

	private String firstNonBlank(String... values) {
		for (String value : values) {
			if (value != null && !value.isBlank()) {
				return value;
			}
		}
		return "";
	}
}

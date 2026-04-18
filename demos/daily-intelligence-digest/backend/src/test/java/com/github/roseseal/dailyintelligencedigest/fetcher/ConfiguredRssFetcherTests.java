package com.github.roseseal.dailyintelligencedigest.fetcher;

import com.github.roseseal.dailyintelligencedigest.config.DailyDigestProperties;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConfiguredRssFetcherTests {

	private final ConfiguredRssFetcher fetcher = new ConfiguredRssFetcher(new DailyDigestProperties());

	@Test
	void parsesRssFeedItems() throws IOException {
		String xml = """
				<rss version="2.0">
				  <channel>
				    <title>Example Feed</title>
				    <item>
				      <guid>item-1</guid>
				      <title>RSS Item</title>
				      <link>https://example.com/rss-item</link>
				      <description><![CDATA[<p>RSS summary</p>]]></description>
				      <pubDate>Wed, 17 Apr 2024 08:30:00 GMT</pubDate>
				    </item>
				  </channel>
				</rss>
				""";

		List<RawItem> items = fetcher.parseFeed("Example RSS", stream(xml), 10);

		assertThat(items).hasSize(1);
		assertThat(items.getFirst().title()).isEqualTo("RSS Item");
		assertThat(items.getFirst().url()).isEqualTo("https://example.com/rss-item");
		assertThat(items.getFirst().content()).isEqualTo("RSS summary");
		assertThat(items.getFirst().source()).isEqualTo("Example RSS");
	}

	@Test
	void parsesAtomFeedEntries() throws IOException {
		String xml = """
				<feed xmlns="http://www.w3.org/2005/Atom">
				  <title>Atom Feed</title>
				  <entry>
				    <id>tag:example.com,2024:1</id>
				    <title>Atom Item</title>
				    <link href="https://example.com/atom-item" rel="alternate"/>
				    <summary>Atom summary</summary>
				    <updated>2024-04-17T09:00:00Z</updated>
				  </entry>
				</feed>
				""";

		List<RawItem> items = fetcher.parseFeed("Example Atom", stream(xml), 10);

		assertThat(items).hasSize(1);
		assertThat(items.getFirst().title()).isEqualTo("Atom Item");
		assertThat(items.getFirst().url()).isEqualTo("https://example.com/atom-item");
		assertThat(items.getFirst().content()).isEqualTo("Atom summary");
		assertThat(items.getFirst().source()).isEqualTo("Example Atom");
	}

	private ByteArrayInputStream stream(String xml) {
		return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
	}
}

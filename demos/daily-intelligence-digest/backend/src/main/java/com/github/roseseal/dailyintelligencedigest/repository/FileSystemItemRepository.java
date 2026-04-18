package com.github.roseseal.dailyintelligencedigest.repository;

import com.github.roseseal.dailyintelligencedigest.config.DailyDigestProperties;
import com.github.roseseal.dailyintelligencedigest.model.NormalizedItem;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileSystemItemRepository implements ItemRepository {

	private static final Logger log = LoggerFactory.getLogger(FileSystemItemRepository.class);

	private static final DateTimeFormatter FILE_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

	private final DailyDigestProperties properties;

	public FileSystemItemRepository(DailyDigestProperties properties) {
		this.properties = properties;
	}

	@Override
	public void saveRawItems(List<RawItem> items) {
		writeReport("raw", "raw-items", renderRawItems(items));
	}

	@Override
	public void saveNormalizedItems(List<NormalizedItem> items) {
		writeReport("normalized", "normalized-items", renderNormalizedItems(items));
	}

	private void writeReport(String category, String filePrefix, String content) {
		LocalDateTime now = LocalDateTime.now();
		Path directory = properties.getOutputDir().resolve(category);
		Path file = directory.resolve(filePrefix + "-" + FILE_TIMESTAMP_FORMAT.format(now) + ".md");
		try {
			Files.createDirectories(directory);
			Files.writeString(file, content);
			log.info("Wrote {} report to {}", category, file.toAbsolutePath());
		}
		catch (IOException e) {
			throw new IllegalStateException("Failed to write " + category + " report to disk", e);
		}
	}

	private String renderRawItems(List<RawItem> items) {
		List<String> lines = new ArrayList<>();
		lines.add("# Raw Items");
		lines.add("");
		lines.add("- Total Items: " + items.size());
		lines.add("");
		for (int index = 0; index < items.size(); index++) {
			RawItem item = items.get(index);
			lines.add("## " + (index + 1) + ". " + item.title());
			lines.add("");
			lines.add("- ID: `" + item.id() + "`");
			lines.add("- Source: " + item.source());
			lines.add("- Published At: " + item.publishTime());
			lines.add("- URL: " + item.url());
			lines.add("- Content: " + item.content());
			lines.add("");
		}
		return String.join(System.lineSeparator(), lines);
	}

	private String renderNormalizedItems(List<NormalizedItem> items) {
		List<String> lines = new ArrayList<>();
		lines.add("# Normalized Items");
		lines.add("");
		lines.add("- Total Items: " + items.size());
		lines.add("");
		for (int index = 0; index < items.size(); index++) {
			NormalizedItem item = items.get(index);
			lines.add("## " + (index + 1) + ". " + item.title());
			lines.add("");
			lines.add("- ID: `" + item.id() + "`");
			lines.add("- Source: " + item.source());
			lines.add("- Score: " + item.score());
			lines.add("- Tags: " + safeTags(item).stream().collect(Collectors.joining(", ")));
			lines.add("- Summary: " + item.summary());
			lines.add("");
		}
		return String.join(System.lineSeparator(), lines);
	}

	private List<String> safeTags(NormalizedItem item) {
		if (item.tags() == null) {
			return Collections.emptyList();
		}
		return item.tags();
	}
}

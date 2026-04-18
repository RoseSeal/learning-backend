package com.github.roseseal.dailyintelligencedigest.repository;

import com.github.roseseal.dailyintelligencedigest.config.DailyDigestProperties;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileSystemItemRepositoryTests {

	@TempDir
	Path tempDir;

	@Test
	void writesRawItemsToMarkdownFile() throws IOException {
		DailyDigestProperties properties = new DailyDigestProperties();
		properties.setOutputDir(tempDir);
		FileSystemItemRepository repository = new FileSystemItemRepository(properties);

		repository.saveRawItems(List.of(new RawItem(
				"raw-1",
				"Hacker News",
				"Example Item",
				"https://example.com/item",
				"Example summary",
				LocalDateTime.of(2024, 4, 17, 8, 30)
		)));

		Path rawDirectory = tempDir.resolve("raw");
		assertThat(rawDirectory).exists();
		List<Path> files = Files.list(rawDirectory).toList();
		assertThat(files).hasSize(1);
		String content = Files.readString(files.getFirst());
		assertThat(content).contains("# Raw Items");
		assertThat(content).contains("Example Item");
		assertThat(content).contains("https://example.com/item");
	}
}

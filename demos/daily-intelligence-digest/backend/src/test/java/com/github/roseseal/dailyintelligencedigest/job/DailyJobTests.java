package com.github.roseseal.dailyintelligencedigest.job;

import com.github.roseseal.dailyintelligencedigest.fetcher.RssFetcher;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import com.github.roseseal.dailyintelligencedigest.repository.ItemRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

class DailyJobTests {

	@Test
	void savesFetchedRawItems() {
		List<RawItem> rawItems = List.of(new RawItem(
				"item-1",
				"Hacker News",
				"Example",
				"https://example.com",
				"summary",
				LocalDateTime.of(2024, 4, 17, 8, 0)
		));
		AtomicReference<List<RawItem>> savedItems = new AtomicReference<>();
		RssFetcher rssFetcher = () -> rawItems;
		ItemRepository itemRepository = new ItemRepository() {
			@Override
			public void saveRawItems(List<RawItem> items) {
				savedItems.set(items);
			}

			@Override
			public void saveNormalizedItems(List<com.github.roseseal.dailyintelligencedigest.model.NormalizedItem> items) {
				// Not used in phase 1.
			}
		};

		new DailyJob(rssFetcher, itemRepository).run();

		assertThat(savedItems.get()).containsExactlyElementsOf(rawItems);
	}
}

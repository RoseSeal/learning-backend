package com.github.roseseal.dailyintelligencedigest.job;

import com.github.roseseal.dailyintelligencedigest.fetcher.RssFetcher;
import com.github.roseseal.dailyintelligencedigest.model.RawItem;
import com.github.roseseal.dailyintelligencedigest.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyJob {

	private static final Logger log = LoggerFactory.getLogger(DailyJob.class);

	private final RssFetcher rssFetcher;
	private final ItemRepository itemRepository;

	public DailyJob(RssFetcher rssFetcher, ItemRepository itemRepository) {
		this.rssFetcher = rssFetcher;
		this.itemRepository = itemRepository;
	}

	public void run() {
		List<RawItem> rawItems = rssFetcher.fetch();
		itemRepository.saveRawItems(rawItems);
		log.info("Saved {} raw items from configured RSS sources", rawItems.size());
	}
}

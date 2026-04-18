package com.github.roseseal.dailyintelligencedigest.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "digest.daily", name = "run-on-startup", havingValue = "true")
public class DailyJobRunner implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(DailyJobRunner.class);

	private final DailyJob dailyJob;

	public DailyJobRunner(DailyJob dailyJob) {
		this.dailyJob = dailyJob;
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("Starting phase 1 daily fetch job");
		dailyJob.run();
		log.info("Finished phase 1 daily fetch job");
	}
}

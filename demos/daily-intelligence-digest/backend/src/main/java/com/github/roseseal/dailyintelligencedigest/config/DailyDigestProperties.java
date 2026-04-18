package com.github.roseseal.dailyintelligencedigest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "digest.daily")
public class DailyDigestProperties {

	private boolean runOnStartup;

	private Path outputDir = Path.of("output");

	private Duration connectTimeout = Duration.ofSeconds(10);

	private Duration readTimeout = Duration.ofSeconds(20);

	private List<RssSource> rssSources = new ArrayList<>();

	public boolean isRunOnStartup() {
		return runOnStartup;
	}

	public void setRunOnStartup(boolean runOnStartup) {
		this.runOnStartup = runOnStartup;
	}

	public Path getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(Path outputDir) {
		this.outputDir = outputDir;
	}

	public Duration getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Duration connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Duration getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(Duration readTimeout) {
		this.readTimeout = readTimeout;
	}

	public List<RssSource> getRssSources() {
		return rssSources;
	}

	public void setRssSources(List<RssSource> rssSources) {
		this.rssSources = rssSources;
	}

	public static class RssSource {

		private String name;

		private String url;

		private int maxItems = 20;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getMaxItems() {
			return maxItems;
		}

		public void setMaxItems(int maxItems) {
			this.maxItems = maxItems;
		}
	}
}

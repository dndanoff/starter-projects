package io.github.dndanoff.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "config")
@Component
@Data
public class AppConfig {
	
	private final Security security = new Security();
	
	@ConfigurationProperties(prefix = "security")
	@Data
	public class Security {
		private String adminUsername;
		private String adminPassword;
	}
}
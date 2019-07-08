package io.github.dndanoff.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "config")
@Component
@Data
public class AppConfig {
	
	private final RetryPolicy retryPolicy = new RetryPolicy();
	private final Db db = new Db();
	private final Jooq jooq = new Jooq();
	
	@ConfigurationProperties(prefix = "jooq")
	@Data
	public class Jooq {
		private String sqlDialect;
	}
	
	@ConfigurationProperties(prefix = "db")
	@Data
	public class Db {
		private String schema;
		private String schemaDefault;
	}
	
	@ConfigurationProperties(prefix = "kafka")
	@Data
	public class Kafka {
		private String truststoreLocation;
		private String truststorePassword;
		private String keystoreLocation;
		private String keystorePassword;
		private String consumerGroupname;
		private String producerId;
		private String bootstrapserver;
		private String registry;
		private Boolean sslEnabled;
		private String manufacturerTopic;
	}
	
	@ConfigurationProperties(prefix = "retry.backoff.kafka.consumer")
	@Data
	public class RetryPolicy {
		private int initialInterval;
		private int multiplier;
		private int maxInterval;
	}
}

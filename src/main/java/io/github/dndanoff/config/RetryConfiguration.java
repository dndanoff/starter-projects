package io.github.dndanoff.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfiguration {

	private final AppConfig appConfig;

	@Autowired
	public RetryConfiguration(AppConfig appConfig) {
		super();
		this.appConfig = appConfig;
	}

	@Bean
	public RetryPolicy pfaAppModuleRetryPolicy() {
		ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();
		Map<Class<? extends Throwable>, RetryPolicy> policyMap = new HashMap<>();
		policyMap.put(RuntimeException.class, new NeverRetryPolicy());
		retryPolicy.setPolicyMap(policyMap);
		return retryPolicy;
	}

	@Bean
	public BackOffPolicy pfaAppModuleBackOffPolicy() {
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(appConfig.getRetryPolicy().getInitialInterval());
		backOffPolicy.setMultiplier(appConfig.getRetryPolicy().getMultiplier());
		backOffPolicy.setMaxInterval(appConfig.getRetryPolicy().getMaxInterval());
		return backOffPolicy;
	}

	@Bean
	public RetryTemplate pfaAppModuleRetryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(pfaAppModuleRetryPolicy());
		retryTemplate.setBackOffPolicy(pfaAppModuleBackOffPolicy());
		return retryTemplate;
	}
}

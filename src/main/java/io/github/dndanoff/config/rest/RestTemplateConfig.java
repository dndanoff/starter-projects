package io.github.dndanoff.config.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

        RestTemplate restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();

        restTemplate.setRequestFactory(requestFactory());
        restTemplate.setInterceptors(interceptors());
        return restTemplate;
    }

    private ClientHttpRequestFactory requestFactory() {
        return new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private List<ClientHttpRequestInterceptor> interceptors() {
        return Collections.singletonList(new LoggingRestInterceptor());
    }
}

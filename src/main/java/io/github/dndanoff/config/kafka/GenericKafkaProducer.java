package io.github.dndanoff.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import io.github.dndanoff.config.AppConfig;
import io.github.dndanoff.core.business_case.exception.KafkaPublishingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericKafkaProducer<K, V> {

	protected final AppConfig appConfig;
	private final KafkaTemplate<K, V> kafkaTemplate;

	public GenericKafkaProducer(KafkaTemplate<K, V> kafkaTemplate, AppConfig appConfig) {
		this.kafkaTemplate = kafkaTemplate;
		this.appConfig = appConfig;
	}

	protected void publishMessage(String topic, K key, V value) throws KafkaPublishingException {
		log.debug("Produce message for key={} with value={} in topic={}", key, value, topic);

		ListenableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, key, value);
		future.addCallback(new ListenableFutureCallback<SendResult<K, V>>() {

			@Override
			public void onSuccess(SendResult<K, V> result) {
				log.debug("Message published successfully with body='{}' and offset={}", value,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Publishing FAILED for message with body='{}'", value, ex);
			}
		});

		try {
			future.get();
		} catch (Exception e) {
			log.error("Could not publish response message for key={}", key, e);
			throw new KafkaPublishingException("Message store exception", e);
		}
	}

}

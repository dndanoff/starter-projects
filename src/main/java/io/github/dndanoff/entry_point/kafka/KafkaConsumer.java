package io.github.dndanoff.entry_point.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {

	@Autowired
	public KafkaConsumer() {
	}
	
	@KafkaListener(topics = "${config.kafka.my-topic}", containerFactory = "stringStringConsumerFactory")
	public void receive(String  payload, @Header(KafkaHeaders.OFFSET) Long offset, 
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
		log.debug("Received command to process with payload={}", payload);
	}

}

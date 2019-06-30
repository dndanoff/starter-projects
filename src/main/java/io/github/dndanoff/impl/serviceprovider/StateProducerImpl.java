package io.github.dndanoff.impl.serviceprovider;

import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.github.dndanoff.config.AppConfig;
import io.github.dndanoff.config.kafka.GenericKafkaProducer;
import io.github.dndanoff.core.business_case.service.StateProducer;
import io.github.dndanoff.core.entity.Entity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StateProducerImpl extends GenericKafkaProducer<String, SpecificRecord> implements StateProducer<Entity> {
	
	@Autowired
	public StateProducerImpl(AppConfig appConfig, KafkaTemplate<String, SpecificRecord> kafkaTemplate) {
		super(kafkaTemplate, appConfig);
	}

	@Override
	public void produceState(Entity entity) {
		// TODO Auto-generated method stub
		
	}



}

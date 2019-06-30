package io.github.dndanoff.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.github.dndanoff.config.AppConfig;

@Configuration
@EnableKafka
public class GenericKafkaConsumerConfig extends KafkaSslConfig {

	@Autowired
	public GenericKafkaConsumerConfig(AppConfig appConfig) {
		super(appConfig);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<SpecificRecord, SpecificRecord> avroKeyAvroValueConsumerFactory() {
		final Map<String, Object> props = createConsumerProperties(KafkaAvroDeserializer.class, KafkaAvroDeserializer.class);
		ConcurrentKafkaListenerContainerFactory<SpecificRecord, SpecificRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));

		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SpecificRecord> stringKeyAvroValueConsumerFactory() {
		final Map<String, Object> props = createConsumerProperties(StringDeserializer.class, KafkaAvroDeserializer.class);
		ConcurrentKafkaListenerContainerFactory<String, SpecificRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));

		return factory;
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> stringKeyStringValueConsumerFactory() {
		final Map<String, Object> props = createConsumerProperties(StringDeserializer.class, StringDeserializer.class);
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));

		return factory;
	}

	private Map<String, Object> createConsumerProperties(Class<? extends Deserializer<?>> keyDes, Class<? extends Deserializer<?>> valueDes){
		Map<String, Object> props = new HashMap<>();
		fillCommonProperties(props);
		fillSchemaRegistryProperties(props);
		fillSslProperties(props);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDes);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDes);
		
		return props;
	}

	private Map<String, Object> fillCommonProperties(Map<String, Object> props){
		props.put(ConsumerConfig.GROUP_ID_CONFIG, appConfig.getKafka().getConsumerGroupname());
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getKafka().getBootstrapserver());
		props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

		return props;
	}

	private Map<String, Object> fillSslProperties(Map<String, Object> props){
		if (appConfig.getKafka().getSslEnabled()) {
			props.putAll(buildSslConfigProperties());
		}

		return props;
	}

	private Map<String, Object> fillSchemaRegistryProperties(Map<String, Object> props){
		if(appConfig.getKafka().getRegistry() != null) {
			props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,appConfig.getKafka().getRegistry());
		}

		return props;
	}
}

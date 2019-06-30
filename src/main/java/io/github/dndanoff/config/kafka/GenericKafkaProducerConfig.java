package io.github.dndanoff.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.github.dndanoff.config.AppConfig;

@Configuration
public class GenericKafkaProducerConfig<K, V> extends KafkaSslConfig{

	@Autowired
	public GenericKafkaProducerConfig(AppConfig appConfig) {
		super(appConfig);
	}

	@Bean
    public ProducerFactory<SpecificRecord, SpecificRecord> avroKeyAvroValueProducerFactory() {
		Map<String, Object> props = createProducerProperties(KafkaAvroSerializer.class, KafkaAvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }
 
    @Bean
    public KafkaTemplate<SpecificRecord, SpecificRecord> avroKeyAvroValueKafkaTemplate(ProducerFactory<SpecificRecord, SpecificRecord> avroKeyAvroValueProducerFactory) {
        return new KafkaTemplate<>(avroKeyAvroValueProducerFactory);
    }
	
	@Bean
    public ProducerFactory<String, SpecificRecord> stringKeyAvroValueProducerFactory() {
		Map<String, Object> props = createProducerProperties(StringSerializer.class, KafkaAvroSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }
 
    @Bean
    public KafkaTemplate<String, SpecificRecord> stringKeyAvroValueKafkaTemplate(ProducerFactory<String, SpecificRecord> stringKeyAvroValueProducerFactory) {
        return new KafkaTemplate<>(stringKeyAvroValueProducerFactory);
    }
	
	private Map<String, Object> createProducerProperties(Class<? extends Serializer<?>> keySerializer, Class<? extends Serializer<?>> valueSerializer) {
        final Map<String, Object> props = new HashMap<>();
        fillCommonProperties(props);
        fillSchemaRegistryProperties(props);
        fillSslProperties(props);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        
        return props;
    }
	
	private Map<String, Object> fillCommonProperties(Map<String, Object> props){
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getKafka().getBootstrapserver());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, appConfig.getKafka().getProducerId());
		
        return props;
	}
	
	private Map<String, Object> fillSslProperties(Map<String, Object> props){
		if (appConfig.getKafka().getSslEnabled()) {
			props.putAll(buildSslConfigProperties());
		}
		
		return props;
	}
	
	private Map<String, Object> fillSchemaRegistryProperties(Map<String, Object> props){
		// Schema Registry location.
		if(appConfig.getKafka().getRegistry() != null) {
			props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, appConfig.getKafka().getRegistry());
		}
		
        return props;
	}
	
}

package br.com.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.kafkaproducer.dto.Cancellation;
import br.com.kafkaproducer.dto.Message;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${KAFKA_BOOTSTRAP_SERVERS}")
	private String bootstrapServers;


	@Bean
	public KafkaTemplate<String, Cancellation> kafkaTemplateTopicTest() {
		return new KafkaTemplate<>(producerFactoryTopicTest());
	}

	@Bean
	public ProducerFactory<String, Cancellation> producerFactoryTopicTest() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public KafkaTemplate<String, Message> kafkaTemplateMessageTest() {
		return new KafkaTemplate<>(producerFactoryMessageTest());
	}
	
	@Bean
	public ProducerFactory<String, Message> producerFactoryMessageTest() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	private Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}
}

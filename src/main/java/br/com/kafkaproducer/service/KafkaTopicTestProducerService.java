package br.com.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import br.com.kafkaproducer.dto.Cancellation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class KafkaTopicTestProducerService {
	
	@Autowired
	private KafkaTemplate<String, Cancellation> kafkaTemplateTopicTest;
	
	@Value("${KAFKA_TOPIC_TEST}")
	private String topic;
	
	public void sendTopitTest(Cancellation cancellation) {
		ListenableFuture<SendResult<String, Cancellation>> callback = kafkaTemplateTopicTest.send(topic, cancellation);
		callback.addCallback(new ListenableFutureCallback<SendResult<String, Cancellation>>() {
			@Override
			public void onSuccess(SendResult<String, Cancellation> result) {
				log.info("mensagem Oenviada com sucesso ao tópico {}", topic);
			}
			@Override
			public void onFailure(Throwable ex) {
				log.error("Falha ao enviar mensagem para o tópico {}", topic, ex);
				throw new RuntimeException("Falha ao enviar para tópico.");
			}
		});
	}

}

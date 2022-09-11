package br.com.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import br.com.kafkaproducer.dto.Message;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class KafkaTopicMessageTestProducerService {
	
	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplateMessageTest;
	
	@Value("${KAFKA_MESSAGE_TEST}")
	public String topicMessageTest;

	public void sendMessageTest(Message message) {
		kafkaTemplateMessageTest.send(topicMessageTest, message)
			.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
				@Override
				public void onSuccess(SendResult<String, Message> result) {
					log.info("Mensagem enviada com sucesso para tópico {}", topicMessageTest);
				}
				@Override
				public void onFailure(Throwable ex) {
					log.error("Falha ao enviar mensagem para o tópico {}", topicMessageTest, ex);
					throw new RuntimeException("Falha ao enviar para tópico.");
				}
			});
	}
	
}

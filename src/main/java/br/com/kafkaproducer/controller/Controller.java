package br.com.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kafkaproducer.dto.Cancellation;
import br.com.kafkaproducer.dto.Message;
import br.com.kafkaproducer.service.KafkaTopicMessageTestProducerService;
import br.com.kafkaproducer.service.KafkaTopicTestProducerService;

@RestController
@RequestMapping("/kafka/send")
public class Controller {
	
	@Autowired
	private KafkaTopicTestProducerService kafkaTopicTestProducerService;
	
	@Autowired
	private KafkaTopicMessageTestProducerService kafkaTopicMessageTestProducerService;
	
	@PostMapping("/topic-test")
	public ResponseEntity<Object> sendTopicTest(@RequestBody Cancellation cancellation) {
		try {
			kafkaTopicTestProducerService.sendTopitTest(cancellation);			
			return ResponseEntity.ok("Mensagem enviada com sucesso.");
		} catch(Exception e) {
			return ResponseEntity.internalServerError()
					.body("Falha ao enviar mensagem ao tópico.");			
		}
	}
	
	@PostMapping("/message-test")
	public ResponseEntity<Object> sendTopicTest(@RequestBody Message message) {
		try {
			kafkaTopicMessageTestProducerService.sendMessageTest(message);			
			return ResponseEntity.ok("Mensagem enviada com sucesso.");
		} catch(Exception e) {
			return ResponseEntity.internalServerError()
					.body("Falha ao enviar mensagem ao tópico.");			
		}
	}

}

package br.com.kafkaproducer.dto;

import lombok.Data;

@Data
public class Message {
	
	private Long messageId;
	private String message;

}

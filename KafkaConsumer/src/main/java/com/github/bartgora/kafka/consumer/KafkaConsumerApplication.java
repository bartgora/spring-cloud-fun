package com.github.bartgora.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}


	@KafkaListener(topics = "kafka-fun", groupId = "fun-group")
	public void receive(Game game){
		System.out.println("Game: " + game.getTitle());
	}
}

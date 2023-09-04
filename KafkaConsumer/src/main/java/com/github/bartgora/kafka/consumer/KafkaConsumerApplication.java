package com.github.bartgora.kafka.consumer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication {

	static final Counter counter = Metrics.counter("kafka.incoming.messages");
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}


	@KafkaListener(topics = "kafka-fun", groupId = "fun-group" )
	public void receive(Game game){
		counter.increment();
		System.out.println("Game: " + game.getTitle() +" Genre: " + game.getGenre());
	}
}

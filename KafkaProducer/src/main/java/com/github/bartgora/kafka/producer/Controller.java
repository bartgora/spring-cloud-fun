package com.github.bartgora.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producer")
public class Controller {

    private final KafkaTemplate<String, Game> kafkaTemplate;

    public Controller(KafkaTemplate<String, Game> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/save")
    public void save(@RequestBody GameRequest gameRequest) {
        kafkaTemplate.send("kafka-fun", new Game(gameRequest.id(), gameRequest.name(), gameRequest.genre()));
    }
}

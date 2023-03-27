package com.example.publisher.controller;

import com.example.publisher.config.PublisherConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publisher")
@Slf4j
public class PublisherController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    ObjectMapper mapper = new ObjectMapper();
    @PostMapping("")
    public ResponseEntity<?> sendMessage(@RequestBody String message){

        rabbitTemplate.setExchange("publisher-exchange-fanout");
//        rabbitTemplate.setRoutingKey("consumer.routing.fail");
        rabbitTemplate.convertAndSend(message);

        log.info(rabbitTemplate.getExchange());
        log.info(rabbitTemplate.getRoutingKey());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

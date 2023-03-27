package com.example.comsumer.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message){
        rabbitTemplate.setExchange("publisher-exchange");
        rabbitTemplate.setRoutingKey("publisher.routing.#");
        rabbitTemplate.convertAndSend(message);
        return new ResponseEntity<>( message, HttpStatus.OK);
    }
}

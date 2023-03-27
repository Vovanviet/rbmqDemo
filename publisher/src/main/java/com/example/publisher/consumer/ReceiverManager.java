package com.example.publisher.consumer;

import com.example.publisher.config.PublisherConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiverManager {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = PublisherConfig.queueName)
    public void receiveMessage(String message) throws JsonProcessingException {
        log.info(message);
    }
}

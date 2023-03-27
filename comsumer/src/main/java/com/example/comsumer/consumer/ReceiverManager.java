package com.example.comsumer.consumer;

import com.example.comsumer.config.ConsumerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiverManager implements RabbitListenerConfigurer {

    @Autowired
    RabbitTemplate rabbitTemplate;
    ObjectMapper mapper = new ObjectMapper();
//    @RabbitListener(queues = ConsumerConfig.queueName)
    public void receiveMessage(String message) throws JsonProcessingException {
        log.info(message);
        rabbitTemplate.setExchange("publisher-exchange");
//        rabbitTemplate.setRoutingKey("publisher.routing.test");
//        rabbitTemplate.convertAndSend("tao đã nhận hàng");
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}

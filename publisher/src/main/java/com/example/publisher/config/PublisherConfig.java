package com.example.publisher.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {
    public static final String queueName = "publisher-queue-fanout";

    public static  final String exchange = "publisher-exchange-fanout";
    public static  final String routingKey = "publisher.routing.test";
//    public static  final String routingKey = "publisher.routing.#";
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queuName);
//        container.setMessageListener(adapter);
//
//        return container;
//    }
//    @Bean
//    MessageListenerAdapter adapter(ReceiverManager receiverManager){
//        return new MessageListenerAdapter(receiverManager,"receiveMessage");
//    }

    @Bean
    Queue queue(){ return new Queue(queueName, false);}

    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange(exchange);
    }
    @Bean
    Binding binding(Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return  connectionFactory;
    }
}


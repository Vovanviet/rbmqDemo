package com.example.comsumer.config;

import com.example.comsumer.consumer.ReceiverManager;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    public static final String queueName = "consumer-queue-fanout";

    public static  final String exchange = "publisher-exchange-fanout";
//    public static  final String routingKey = "consumer.routing.test";
//    public static  final String routingKey = "consumer.routing.#";
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(adapter);
        return container;
    }
    @Bean
    MessageListenerAdapter adapter(ReceiverManager receiverManager){
        return new MessageListenerAdapter(receiverManager,"receiveMessage");
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


//    @Bean
//    Queue queue(){ return new Queue(queuName, false);}
//
//    @Bean
//    TopicExchange topicExchange(){
//        return new TopicExchange(topicExchange);
//    }
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
//    }

}

package com.rabbitmq.springbootrabbitmqdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;

import org.springframework.context.annotation.Bean;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    public String QUEUE;

    @Value("${rabbitmq.exchange.name}")
    public String EXCHANGE;

    @Value("${rabbitmq.routing-key.name}")
    public String ROUTING_KEY;

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

}

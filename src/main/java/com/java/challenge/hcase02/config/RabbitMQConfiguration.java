package com.java.challenge.hcase02.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class RabbitMQConfiguration {

    @Value("${spring-rabbitmq.rabbit.exchange.name}")
    private String exchangeName;
    @Value("${spring-rabbitmq.rabbit.msg.queue.name}")
    private String messageQueue;
    @Value("${spring-rabbitmq.rabbit.msg.routing.name}")
    private String messageRoutingName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(messageQueue);
    }

    @Bean
    public Binding messageBinding(final Queue messageQueue, final DirectExchange directExchange) {
        return BindingBuilder
                .bind(messageQueue)
                .to(directExchange)
                .with(messageRoutingName);
    }
}
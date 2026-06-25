package com.commerce.server.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME="shopify_queue";
    public static final String QUEUE_EXCHANGE = "shopify_exchange";
    public static final String QUEUE_ROUTING_KEY="shopify_routing_key";

    @Bean
    Queue queue(){
        return new Queue(QUEUE_NAME);
    }
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(QUEUE_EXCHANGE);
    }
    @Bean
    Binding binding(Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_ROUTING_KEY);
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory factory){
        final RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}

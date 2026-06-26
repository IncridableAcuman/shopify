package com.commerce.server.util.mail.producer;

import com.commerce.server.config.RabbitMQConfig;
import com.commerce.server.util.mail.dto.EmailPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate template;

    public void sendMessageWithRabbitMQ(EmailPayload payload){
       template
               .convertAndSend(
                       RabbitMQConfig.QUEUE_EXCHANGE,
                       RabbitMQConfig.QUEUE_ROUTING_KEY,
                       payload
               );

    }
}

package com.example.demo.producers;

import com.example.demo.models.Mail;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailProducer {

    @Value("${broker.queue.email.name}")
    private String routing_key;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMessageMail(Mail mail){
        try {
            rabbitTemplate.convertAndSend("", routing_key, mail);

        }catch(AmqpException e){
            e.getStackTrace();
        }
    }
}

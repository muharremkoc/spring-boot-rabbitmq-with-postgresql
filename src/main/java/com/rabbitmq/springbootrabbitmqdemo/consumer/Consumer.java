package com.rabbitmq.springbootrabbitmqdemo.consumer;

import com.rabbitmq.springbootrabbitmqdemo.models.message.Message;
import com.rabbitmq.springbootrabbitmqdemo.services.message.MessageService;
import com.rabbitmq.springbootrabbitmqdemo.services.session.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Consumer {
    final SimpMessagingTemplate messagingTemplate;
    final SessionService sessionService;
    final MessageService messageService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(Message message) {
        System.out.println("Consumed Message ====> " + message.toString());

        if (sessionService.isConnected(message.getReceiver())) {
            CompletableFuture.runAsync(() -> {
                try {
                    messageService.deliveredMessage(message.getId());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiver().getId()) ,"/queue/messages", message );
    }


}

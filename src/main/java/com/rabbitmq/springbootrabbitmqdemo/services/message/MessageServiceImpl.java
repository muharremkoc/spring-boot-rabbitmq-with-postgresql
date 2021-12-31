package com.rabbitmq.springbootrabbitmqdemo.services.message;


import com.rabbitmq.springbootrabbitmqdemo.dto.SendDTO;
import com.rabbitmq.springbootrabbitmqdemo.enums.State;
import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import com.rabbitmq.springbootrabbitmqdemo.models.message.Message;
import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import com.rabbitmq.springbootrabbitmqdemo.repository.MessageRepository;
import com.rabbitmq.springbootrabbitmqdemo.services.conversation.ConversationService;
import com.rabbitmq.springbootrabbitmqdemo.services.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageServiceImpl implements  MessageService{

    final MessageRepository messageRepository;
    final UserService userService;
    final ConversationService conversationService;
    final RabbitTemplate rabbitTemplate;

    @Override
    public Message send(SendDTO sendDTO) throws NotFoundException {

        User receiver=userService.findUserById(sendDTO.getReceiverID());
        User sender=userService.findUserById(sendDTO.getSenderID());
        Conversation conversation=null;

        if(sendDTO.getConversationID() > 0)
            conversation = conversationService.getExist(sender.getId(), receiver.getId());
        else
            conversation = conversationService
                    .create(Conversation.builder().sender(sender).receiver(receiver).build());

        Message message = Message.builder()
                .message(sendDTO.getMessage())
                .conversation(conversation)
                .receiver(receiver)
                .sender(sender)
                .state(State.send)
                .sendDate(new Date()).build();

        Message savedMessage = messageRepository.save(message);
        if(savedMessage != null)
            rabbitTemplate.convertAndSend("messageExchange", "messageRoutingKey", savedMessage);

        return savedMessage;
    }


    @Override
    public boolean seeMessage(long messageID) throws NotFoundException {
        Message message = messageRepository.findById(messageID)
                .orElseThrow(() -> new NotFoundException("Message not found"));

        message.setSee(true);
        Message savedMessage = messageRepository.save(message);

        return savedMessage.isSee();
    }

    @Override
    public State deliveredMessage(long messageID) throws NotFoundException {
        Message message = messageRepository.findById(messageID)
                .orElseThrow(() -> new NotFoundException("Message not found"));

        message.setState(State.delivered);
        Message savedMessage = messageRepository.save(message);

        return savedMessage.getState();
    }
}

package com.rabbitmq.springbootrabbitmqdemo.services.message;


import com.rabbitmq.springbootrabbitmqdemo.dto.SendDTO;
import com.rabbitmq.springbootrabbitmqdemo.enums.State;
import com.rabbitmq.springbootrabbitmqdemo.models.message.Message;
import org.webjars.NotFoundException;


public interface MessageService {

    Message send(SendDTO sendDTO) throws NotFoundException;

    boolean seeMessage(long messageID) throws NotFoundException;

    State deliveredMessage(long messageID) throws NotFoundException;
}

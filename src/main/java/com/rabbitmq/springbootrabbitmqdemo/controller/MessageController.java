package com.rabbitmq.springbootrabbitmqdemo.controller;

import com.rabbitmq.springbootrabbitmqdemo.dto.SendDTO;
import com.rabbitmq.springbootrabbitmqdemo.models.message.Message;
import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import com.rabbitmq.springbootrabbitmqdemo.services.message.MessageService;
import com.rabbitmq.springbootrabbitmqdemo.services.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/messages")
public class MessageController {

    final MessageService messageService;

    final UserService userService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) throws NotFoundException{
        return userService.saveUser(user);
    }

    @PostMapping("/send")
    public Message send(@RequestBody SendDTO sendDTO) throws NotFoundException{
        return messageService.send(sendDTO);
    }
    @PostMapping(value = "/seen-message/{messageId}")
    public boolean sendMessages(@PathVariable long messageId) throws NotFoundException {
        return messageService.seeMessage(messageId);
    }
}

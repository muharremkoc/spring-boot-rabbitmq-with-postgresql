package com.rabbitmq.springbootrabbitmqdemo.controller;

import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import com.rabbitmq.springbootrabbitmqdemo.services.conversation.ConversationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    final ConversationService conversationService;
    @GetMapping(value = "/{userId}")
    public List<Conversation> findAllConversationByUserId(@PathVariable("userId") long userId) throws NotFoundException {
        return conversationService.findBySenderUserIDOrReceiverUserId(userId);
    }
}

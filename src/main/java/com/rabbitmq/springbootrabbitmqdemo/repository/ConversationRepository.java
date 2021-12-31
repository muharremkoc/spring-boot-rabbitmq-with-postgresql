package com.rabbitmq.springbootrabbitmqdemo.repository;

import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {

    Conversation findBySenderIdAndReceiverId(long senderId, long receiverId);

    List<Conversation> findBySenderIdOrReceiverId(long senderId, long receiverId);
}

package com.rabbitmq.springbootrabbitmqdemo.repository;

import com.rabbitmq.springbootrabbitmqdemo.models.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}

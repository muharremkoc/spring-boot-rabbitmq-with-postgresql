package com.rabbitmq.springbootrabbitmqdemo.models.message;

import com.rabbitmq.springbootrabbitmqdemo.enums.State;
import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {

    @Id
    @GeneratedValue
    long id;

    String message;

    boolean isSee;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    User receiver;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    Conversation conversation;

    Date sendDate;
}

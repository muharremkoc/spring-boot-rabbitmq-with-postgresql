package com.rabbitmq.springbootrabbitmqdemo.models.conversation;


import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "conversations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
     User receiver;

}



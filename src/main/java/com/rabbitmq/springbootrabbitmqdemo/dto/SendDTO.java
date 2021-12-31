package com.rabbitmq.springbootrabbitmqdemo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendDTO {
      String message;

      long senderID;

      long receiverID;

      long conversationID;
}

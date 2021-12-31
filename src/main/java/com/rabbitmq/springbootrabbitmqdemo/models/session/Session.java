package com.rabbitmq.springbootrabbitmqdemo.models.session;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session implements Serializable {

    static final long serialVersionUID=1L;

    String userName;
    String sessionId;
    String subscriptionId;
    String destination;

}


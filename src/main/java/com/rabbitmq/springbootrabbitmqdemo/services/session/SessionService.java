package com.rabbitmq.springbootrabbitmqdemo.services.session;

import com.rabbitmq.springbootrabbitmqdemo.models.user.User;

public interface SessionService {

    boolean isConnected(User user);

    void connectUser(String userName,String sessionID);

    void disconnectUser(String sessionID);

    void subscribeUser(String sessionID,String subscriptionID,String destination);
}

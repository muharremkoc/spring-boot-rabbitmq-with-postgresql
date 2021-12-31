package com.rabbitmq.springbootrabbitmqdemo.services.session;

import com.rabbitmq.springbootrabbitmqdemo.models.session.Session;
import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionServiceImpl implements SessionService{

    final RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean isConnected(User user) {
        return redisTemplate.hasKey(user.getName());
    }

    @Override
    public void connectUser(String userName, String sessionID) {
        Session session= Session.builder()
                .userName(userName)
                .sessionId(sessionID).build();
        System.out.println("User Connected:\n"+"UserName:"+session.getUserName()+"\n SessionID:"+session.getSessionId());
    }

    @Override
    public void disconnectUser(String sessionID) {
        if (redisTemplate.hasKey(sessionID)) {
            Session session = (Session) redisTemplate.opsForValue().get(sessionID);
            System.out.println("User Disconnected: " + session.getUserName() + " Session Id: " + sessionID);

            redisTemplate.delete(sessionID);
            redisTemplate.delete(session.getUserName());
        }
    }

    @Override
    public void subscribeUser(String sessionID, String subscriptionID, String destination) {
        if (redisTemplate.hasKey(sessionID)) {
            Session session = (Session) redisTemplate.opsForValue().get(sessionID);
            session.setSubscriptionId(subscriptionID);
            session.setDestination(destination);

            System.out.println("Subscribed user: " + session.getUserName() +
                    " Session Id: " + sessionID +
                    " Subscription Id: " + subscriptionID +
                    " Simp Destination: " + destination);

            redisTemplate.opsForValue().set(sessionID, session);
            redisTemplate.opsForValue().set(session.getUserName(), sessionID);
        }
    }
}

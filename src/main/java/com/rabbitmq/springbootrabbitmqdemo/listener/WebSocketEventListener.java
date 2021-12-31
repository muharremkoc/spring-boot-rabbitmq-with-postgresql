package com.rabbitmq.springbootrabbitmqdemo.listener;

import com.rabbitmq.springbootrabbitmqdemo.services.session.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.ArrayList;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebSocketEventListener {

    final SessionService sessionService;
    @EventListener
    private void handleSessionConnect(SessionConnectEvent event) {
        Map<String, ArrayList> nativeHeaders = (Map<String, ArrayList>) event.getMessage().getHeaders().get("nativeHeaders");
        var user = nativeHeaders.get("user");
        String simpSessionId = (String) event.getMessage().getHeaders().get("sessionID");
        String userName = (String) user.get(0);
        sessionService.connectUser(userName, simpSessionId);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println(event.getSessionId());
        sessionService.disconnectUser(event.getSessionId());
    }

    @EventListener
    private void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        String simpSessionId = (String) event.getMessage().getHeaders().get("sessionID");
        String simpSubscriptionId = (String) event.getMessage().getHeaders().get("subscriptionID");
        String simpDestination = (String) event.getMessage().getHeaders().get("destination");

        sessionService.subscribeUser(simpSessionId, simpSubscriptionId, simpDestination);
    }
}

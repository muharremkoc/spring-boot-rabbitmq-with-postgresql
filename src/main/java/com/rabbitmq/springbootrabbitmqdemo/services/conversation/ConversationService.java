package com.rabbitmq.springbootrabbitmqdemo.services.conversation;

import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import org.webjars.NotFoundException;

import java.util.List;

public interface ConversationService {
    Conversation findBySenderUserIDAndReceiverUserID(long senderID,long receiverID );

    Conversation create(Conversation conversation);

    Conversation getExist(long senderID,long receiverID);

    List<Conversation> findBySenderUserIDOrReceiverUserId(long userID) throws NotFoundException;

}

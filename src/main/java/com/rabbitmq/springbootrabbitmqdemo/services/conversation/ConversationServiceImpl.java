package com.rabbitmq.springbootrabbitmqdemo.services.conversation;

import com.rabbitmq.springbootrabbitmqdemo.models.conversation.Conversation;
import com.rabbitmq.springbootrabbitmqdemo.repository.ConversationRepository;
import com.rabbitmq.springbootrabbitmqdemo.services.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationServiceImpl implements ConversationService{

    final ConversationRepository conversationRepository;
    final UserService userService;

    @Override
    public Conversation findBySenderUserIDAndReceiverUserID(long senderID, long receiverID) {
        return conversationRepository.findBySenderIdAndReceiverId(senderID, receiverID);
    }

    @Override
    public Conversation create(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation getExist(long senderID, long receiverID) {
        Conversation sendersConversation = findBySenderUserIDAndReceiverUserID(senderID, receiverID);
        Conversation recipientsConversation = findBySenderUserIDAndReceiverUserID(receiverID, senderID);
        return sendersConversation != null ? sendersConversation: recipientsConversation;
    }

    @Override
    public List<Conversation> findBySenderUserIDOrReceiverUserId(long userID) throws NotFoundException {
        userService.findUserById(userID);

        return conversationRepository.findBySenderIdOrReceiverId(userID, userID);
    }
}

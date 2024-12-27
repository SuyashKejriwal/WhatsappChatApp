package com.example.whatsapp_backend.messaging.domain.message.repository;

import java.util.List;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Conversation;
import com.example.whatsapp_backend.messaging.domain.message.aggregate.Message;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSendState;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;

public interface MessageRepository {
	
	Message save(Message message, User sender, Conversation conversation);

    int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state);

    List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId);

}

package com.example.whatsapp_backend.infrastructure.secondary.dao;

import java.util.List;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Conversation;
import com.example.whatsapp_backend.messaging.domain.message.aggregate.Message;
import com.example.whatsapp_backend.messaging.domain.message.repository.MessageRepository;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSendState;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;

public class MessageDao implements MessageRepository {

	@Override
	public Message save(Message message, User sender, Conversation conversation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId,
			MessageSendState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId,
			UserPublicId userPublicId) {
		// TODO Auto-generated method stub
		return null;
	}

}

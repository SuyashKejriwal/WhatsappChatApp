package com.example.whatsapp_backend.infrastructure.secondary.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Conversation;
import com.example.whatsapp_backend.messaging.domain.message.aggregate.ConversationToCreate;
import com.example.whatsapp_backend.messaging.domain.message.repository.ConversationRepository;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;

public class ConversationDao implements ConversationRepository{

	@Override
	public Conversation save(ConversationToCreate conversation, List<User> members) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conversation> get(ConversationPublicId conversationPublicId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Page<Conversation> getConversationByUserPublicId(UserPublicId publicId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByPublicId(UserPublicId userPublicId, ConversationPublicId conversationPublicId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Conversation> getConversationByUsersPublicIdAndPublicId(UserPublicId userPublicId,
			ConversationPublicId conversationPublicId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Conversation> getConversationByUserPublicIds(List<UserPublicId> publicIds) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Conversation> getOneByPublicId(ConversationPublicId conversationPublicId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}

package com.example.whatsapp_backend.infrastructure.secondary.dao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.repository.UserRepository;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserEmail;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;

public class UserDao implements UserRepository{

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> get(UserPublicId userPublicId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> getOneByEmail(UserEmail userEmail) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> getByPublicIds(List<UserPublicId> userPublicIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> search(Pageable pageable, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateLastSeenByPublicId(UserPublicId userPublicId, Instant lastSeen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getRecipientByConversationIdExcludingReader(ConversationPublicId conversationPublicId,
			UserPublicId readerPublicId) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.example.whatsapp_backend.messaging.domain.user.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserEmail;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;

public interface UserRepository {
	
	void save(User user);
	
	Optional<User> get(UserPublicId userPublicId);
	
	Optional<User> getOneByEmail(UserEmail userEmail);
	
	List<User> getByPublicIds(List<UserPublicId> userPublicIds);
	
	Page<User> search(Pageable pageable,String query);
	
	int updateLastSeenByPublicId(UserPublicId userPublicId,Instant lastSeen);
	
	List<User> getRecipientByConversationIdExcludingReader(ConversationPublicId conversationPublicId,UserPublicId readerPublicId);

}

package com.example.whatsapp_backend.messaging.domain.message.vo;

import java.util.UUID;

import org.springframework.util.Assert;

public record ConversationPublicId(UUID value) {
	
	public ConversationPublicId{
		Assert.notNull(value, "conversation cannot be null");
	}

}

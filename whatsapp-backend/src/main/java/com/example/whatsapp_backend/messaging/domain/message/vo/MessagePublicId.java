package com.example.whatsapp_backend.messaging.domain.message.vo;

import java.util.UUID;

import org.springframework.util.Assert;

public record MessagePublicId(UUID value) {
	
	public MessagePublicId{
		Assert.notNull(value, "Id can't be null");
	}

}

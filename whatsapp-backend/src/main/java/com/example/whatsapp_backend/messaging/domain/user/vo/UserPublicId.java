package com.example.whatsapp_backend.messaging.domain.user.vo;

import java.util.UUID;

import org.springframework.util.Assert;


public record UserPublicId(UUID value) {
	public UserPublicId {
		Assert.notNull(value, "value cannot be null");
	}
}

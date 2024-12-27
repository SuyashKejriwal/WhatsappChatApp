package com.example.whatsapp_backend.messaging.domain.user.vo;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record AuthorityName(String name) {
	public AuthorityName {
		Assert.field("name", name).notNull();
	}
}

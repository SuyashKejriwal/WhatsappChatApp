package com.example.whatsapp_backend.messaging.domain.message.aggregate;

import java.util.List;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record Messages(List<Message> messages) {
	
	public Messages{
		Assert.field("messages",messages).notNull().noNullElement();
	}
}

package com.example.whatsapp_backend.messaging.domain.message.vo;

import java.time.Instant;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record MessageSentTime(Instant date) {
	
	public MessageSentTime{
		Assert.field("date", date).notNull();
	}

}

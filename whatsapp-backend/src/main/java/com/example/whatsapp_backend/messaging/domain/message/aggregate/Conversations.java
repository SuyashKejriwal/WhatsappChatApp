package com.example.whatsapp_backend.messaging.domain.message.aggregate;

import java.util.List;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record Conversations(List<Conversation> conversations) {
	
	 public Conversations {
	        Assert.field("conversations", conversations).notNull().noNullElement();
	    }
}

package com.example.whatsapp_backend.messaging.domain.message.aggregate;

import com.example.whatsapp_backend.messaging.domain.message.vo.MessageContent;

public record MessageSendNew(MessageContent messageContent,
		Conversation conversationPublicId) {

}

package com.example.whatsapp_backend.messaging.domain.message.aggregate;

import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageContent;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessagePublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSendState;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSentTime;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.error.domain.Assert;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {

	private final MessageSentTime sentTime;

	private final MessageContent content;

	private final MessageSendState sendState;

	private final MessagePublicId publicId;

	private final UserPublicId sender;

	private final ConversationPublicId conversationId;

	public Message(MessageSentTime sentTime, MessageContent content, MessageSendState sendState,
			MessagePublicId publicId, UserPublicId sender, ConversationPublicId conversationId) {
		assertMandatoryFields(sentTime, content, sendState, publicId, sender, conversationId);
		this.sentTime = sentTime;
		this.content = content;
		this.sendState = sendState;
		this.publicId = publicId;
		this.sender = sender;
		this.conversationId = conversationId;
	}

	private void assertMandatoryFields(MessageSentTime sentTime, MessageContent content, MessageSendState state,
			MessagePublicId publicId, UserPublicId sender, ConversationPublicId conversationId) {
		Assert.notNull("sentTime", sentTime);
		Assert.notNull("content", content);
		Assert.notNull("state", state);
		Assert.notNull("publicId", publicId);
		Assert.notNull("sender", sender);
		Assert.notNull("conversationId", conversationId);
	}
}

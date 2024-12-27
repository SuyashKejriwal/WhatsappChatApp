package com.example.whatsapp_backend.messaging.domain.message.aggregate;

import java.util.Set;

import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.error.domain.Assert;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConversationToCreate {
	
	private final Set<UserPublicId> members;
	
	private final ConversationName name;

	public ConversationToCreate(Set<UserPublicId> members, ConversationName name) {
		assertMandatoryFields(members,name);
		this.members = members;
		this.name = name;
	}
	
	private void assertMandatoryFields(Set<UserPublicId> members,ConversationName name) {
		Assert.notNull("name", name);
		Assert.notNull("members",members);
	}
}

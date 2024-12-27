package com.example.whatsapp_backend.infrastructure.secondary.document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jilt.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Conversation;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Document(collection = "conversation") // MongoDB collection name
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDocument {

	@Id
	private String id; // MongoDB ObjectId

	@Field("public_id")
	private UUID publicId; // UUID for public-facing ID

	@Field("name")
	private String name;

	@Field("messages")
	private Set<String> messageIds = new HashSet<>(); // References to MessageDocument IDs

	@Field("users")
	private Set<String> userIds = new HashSet<>(); // References to UserDocument IDs
	
	 public static ConversationDocument from(Conversation conversation) {
	        ConversationDocumentBuilder conversationDocumentBuilder = ConversationDocumentBuilder.conversationDocument();

	        if (conversation.getDbId() != null) {
	            conversationDocumentBuilder.id(String.valueOf(conversation.getDbId()));
	        }

	        if (conversation.getConversationName() != null) {
	            conversationDocumentBuilder.name(conversation.getConversationName().name());
	        }

	        if (conversation.getConversationPublicId() != null) {
	            conversationDocumentBuilder.publicId(conversation.getConversationPublicId().value());
	        }

	        if (conversation.getMessages() != null) {
	            Set<MessageEntity> messages = conversation.getMessages()
	                    .stream().map(MessageEntity::from).collect(Collectors.toSet());
	            conversationEntityBuilder.messages(messages);
	        }

	        conversationDocumentBuilder.users(UserDocument.from(conversation.getMembers().stream().toList()));

	        return conversationDocumentBuilder.build();
	    }

	    public static Conversation toDomain(ConversationEntity conversation) {
	        ConversationBuilder conversationEntityBuilder = ConversationBuilder
	                .conversation()
	                .conversationPublicId(new ConversationPublicId(conversation.getPublicId()))
	                .conversationName(new ConversationName(conversation.getName()))
	                .members(UserEntity.toDomain(conversation.getUsers()))
	                .dbId(conversation.getId());

	        if (conversation.getMessages() != null) {
	            conversationEntityBuilder.messages(MessageEntity.toDomain(conversation.getMessages()));
	        }

	        return conversationEntityBuilder.build();
	    }

	    public static ConversationEntity from(ConversationToCreate conversation) {
	        ConversationEntityBuilder conversationEntityBuilder = ConversationEntityBuilder.conversationEntity();

	        if (conversation.getName() != null) {
	            conversationEntityBuilder.name(conversation.getName().name());
	        }

	        return conversationEntityBuilder.build();
	    }
}

package com.example.whatsapp_backend.infrastructure.secondary.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.UuidGenerator;
import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Conversation;
import com.example.whatsapp_backend.messaging.domain.message.aggregate.ConversationBuilder;
import com.example.whatsapp_backend.messaging.domain.message.aggregate.ConversationToCreate;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationName;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "conversation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConversationEntity extends AbstractAuditingEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversationSequenceGenerator")
	@SequenceGenerator(name = "conversationSequenceGenerator", sequenceName = "conversation_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@UuidGenerator
	@Column(name = "public_id", nullable = false, updatable = false)
	private UUID publicId;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation")
	private Set<MessageEntity> messages = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_conversation", joinColumns = {
			@JoinColumn(name = "conversation_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	private Set<UserEntity> users = new HashSet<>();

	public static ConversationEntity from(Conversation conversation) {
		ConversationEntityBuilder conversationEntityBuilder = ConversationEntityBuilder.conversationEntity();

		if (conversation.getDbId() != null) {
			conversationEntityBuilder.id(conversation.getDbId());
		}

		if (conversation.getConversationName() != null) {
			conversationEntityBuilder.name(conversation.getConversationName().name());
		}

		if (conversation.getConversationPublicId() != null) {
			conversationEntityBuilder.publicId(conversation.getConversationPublicId().value());
		}

		if (conversation.getMessages() != null) {
			Set<MessageEntity> messages = conversation.getMessages().stream().map(MessageEntity::from)
					.collect(Collectors.toSet());
			conversationEntityBuilder.messages(messages);
		}

		conversationEntityBuilder.users(UserEntity.from(conversation.getMembers().stream().toList()));

		return conversationEntityBuilder.build();
	}

	public static Conversation toDomain(ConversationEntity conversation) {
		ConversationBuilder conversationEntityBuilder = ConversationBuilder.conversation()
				.conversationPublicId(new ConversationPublicId(conversation.getPublicId()))
				.conversationName(new ConversationName(conversation.getName()))
				.members(UserEntity.toDomain(conversation.getUsers())).dbId(conversation.getId());

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

	@Override
	public Long getId() {
		return id;
	}
}

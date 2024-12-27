package com.example.whatsapp_backend.infrastructure.secondary.document;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jilt.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.whatsapp_backend.messaging.domain.message.aggregate.Message;
import com.example.whatsapp_backend.messaging.domain.message.vo.ConversationPublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageContent;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageMediaContent;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessagePublicId;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSendState;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageSentTime;
import com.example.whatsapp_backend.messaging.domain.message.vo.MessageType;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDocument extends AbstractAuditingEntity<String>{
	

    @Id
    private String id; // MongoDB uses String for primary keys by default

    @Field("public_id")
    private UUID publicId;

    @Field("send_time")
    private Instant sendTime;

    @Field("text")
    private String text;

    @Field("type")
    private MessageType type;

    @Field("send_state")
    private MessageSendState sendState;

    @DBRef
    @Field("user_fk_sender")
    private UserDocument sender;

    @DBRef
    @Field("conversation_fk")
    private ConversationDocument conversation;

    @DBRef
    @Field("message_binary_content_fk")
    private MessageContentBinaryDocument contentBinary;
    
    public static MessageDocument from(Message message) {
        MessageDocumentBuilder messageDocumentBuilder = MessageDocumentBuilder.messageDocument();

        if (message.getContent().type().equals(MessageType.TEXT)) {
            messageDocumentBuilder.text(message.getContent().text());
        } else {
            messageDocumentBuilder
                    .contentBinary(MessageContentBinaryDocument.from(message.getContent()));
            if (message.getContent().text() != null) {
                messageDocumentBuilder.text(message.getContent().text());
            }
        }

        UserDocument user = UserDocumentBuilder.userDocument()
                .publicId(message.getSender().value()).build();

        messageDocumentBuilder
                .type(message.getContent().type())
                .publicId(message.getPublicId().value())
                .sendTime(message.getSentTime().date())
                .sendState(message.getSendState())
                .sender(user);

        return messageDocumentBuilder.build();
    }

    public static Message toDomain(MessageDocument messageDocument) {
        MessageBuilder messageBuilder = MessageBuilder.message()
                .conversationId(new ConversationPublicId(messageEntity.getPublicId()))
                .sendState(messageEntity.getSendState())
                .sentTime(new MessageSentTime(messageEntity.getSendTime()))
                .sender(new UserPublicId(messageEntity.getSender().getPublicId()))
                .publicId(new MessagePublicId(messageEntity.getPublicId()));

        if (messageEntity.getType().equals(MessageType.TEXT)) {
            MessageContent content = new MessageContent(messageEntity.getText(), MessageType.TEXT, null);
            messageBuilder.content(content);
        } else {
            MessageMediaContent messageMediaContent = new
                    MessageMediaContent(messageEntity.getContentBinary().getFile(),
                    messageEntity.getContentBinary().getFileContentType());
            MessageContent content = new MessageContent(messageEntity.getText(), messageEntity.getType(), messageMediaContent);
            messageBuilder.content(content);
        }

        return messageBuilder.build();
    }

    public static Set<Message> toDomain(Set<MessageEntity> messages) {
        return messages.stream().map(MessageEntity::toDomain).collect(Collectors.toSet());
    }


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}

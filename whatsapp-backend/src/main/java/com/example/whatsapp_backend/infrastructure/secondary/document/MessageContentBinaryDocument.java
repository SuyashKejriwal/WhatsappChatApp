package com.example.whatsapp_backend.infrastructure.secondary.document;

import org.jilt.Builder;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.whatsapp_backend.messaging.domain.message.vo.MessageContent;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MessageContentBinaryDocument extends AbstractAuditingEntity<String>{
	
	@Id
    private String id; // MongoDB ObjectId or custom sequence

    @Field("file")
    private byte[] file; // Binary data stored directly in the document

    @Field("file_content_type")
    private String fileContentType; // MIME type of the file

    @Field("message_id")
    private String messageId; // Reference to the associated MessageDocume

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	 public static MessageContentBinaryDocument from(MessageContent messageContent) {
	        return MessageContentBinaryDocumentBuilder.messageContentBinaryDocument()
	                .fileContentType(messageContent.media().mimetype())
	                .file(messageContent.media().file())
	                .build();
	    }

}

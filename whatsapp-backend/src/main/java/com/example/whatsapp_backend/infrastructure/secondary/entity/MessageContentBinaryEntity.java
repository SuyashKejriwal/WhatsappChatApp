package com.example.whatsapp_backend.infrastructure.secondary.entity;

import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.message.vo.MessageContent;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "message_binary_content")
@Builder
@Data
@AllArgsConstructor
public class MessageContentBinaryEntity extends AbstractAuditingEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messageContentBinarySequenceGenerator")
    @SequenceGenerator(name = "messageContentBinarySequenceGenerator",
            sequenceName = "message_binary_content_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @OneToOne(mappedBy = "contentBinary")
    private MessageEntity message;

    public static MessageContentBinaryEntity from(MessageContent messageContent) {
        return MessageContentBinaryEntityBuilder.messageContentBinaryEntity()
                .fileContentType(messageContent.media().mimetype())
                .file(messageContent.media().file())
                .build();
    }

    public Long getId() {
        return id;
    }
	
}

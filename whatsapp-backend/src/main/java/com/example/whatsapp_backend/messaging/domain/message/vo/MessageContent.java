package com.example.whatsapp_backend.messaging.domain.message.vo;


public record MessageContent(String text,
		MessageType type,
		MessageMediaContent media) {

}

package com.example.whatsapp_backend.infrastructure.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.whatsapp_backend.infrastructure.secondary.entity.MessageContentBinaryEntity;

public interface MessageBinaryContentRepository extends JpaRepository<MessageContentBinaryEntity, Long>{

}

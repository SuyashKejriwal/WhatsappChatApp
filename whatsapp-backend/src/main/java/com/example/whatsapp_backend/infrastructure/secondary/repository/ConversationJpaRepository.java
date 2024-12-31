package com.example.whatsapp_backend.infrastructure.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.whatsapp_backend.infrastructure.secondary.entity.ConversationEntity;

public interface ConversationJpaRepository extends JpaRepository<ConversationEntity, Long>{

}

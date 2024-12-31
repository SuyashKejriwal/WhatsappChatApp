package com.example.whatsapp_backend.infrastructure.secondary.entity;

import java.util.Set;
import java.util.stream.Collectors;

import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.user.aggregate.Authority;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.AuthorityBuilder;
import com.example.whatsapp_backend.messaging.domain.user.vo.AuthorityName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityEntity {

	 private static final long serialVersionUID = 1L;

	    @NotNull
	    @Size(max = 50)
	    @Id
	    @Column(length = 50)
	    private String name;

	    

	    public static Set<AuthorityEntity> from(Set<Authority> authorities) {
	        return authorities
	                .stream()
	                .map(authority ->
	                        AuthorityEntityBuilder.authorityEntity()
	                                .name(authority.getName().name()).build()).collect(Collectors.toSet());

	    }

	    public static Set<Authority> toDomain(Set<AuthorityEntity> authorityEntities) {
	        return authorityEntities.stream()
	                .map(authority -> AuthorityBuilder.authority()
	                        .name(new AuthorityName(authority.name)).build())
	                .collect(Collectors.toSet());
	    }	
}
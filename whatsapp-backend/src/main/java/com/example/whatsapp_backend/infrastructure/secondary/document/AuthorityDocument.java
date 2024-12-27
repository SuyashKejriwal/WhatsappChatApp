package com.example.whatsapp_backend.infrastructure.secondary.document;

import java.util.Set;
import java.util.stream.Collectors;

import org.jilt.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.whatsapp_backend.infrastructure.secondary.document.AuthorityDocumentBuilder;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.Authority;
import com.example.whatsapp_backend.messaging.domain.user.vo.AuthorityName;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.AuthorityBuilder;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "authority")// MongoDB collection name
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDocument {

	@Id
	@NotNull
	@Size(max = 50)
	private String name;
	
	public static Set<AuthorityDocument> from(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(authority-> AuthorityDocumentBuilder.authorityDocument().name(authority.getName().name()).build())
                .collect(Collectors.toSet());

    }

    public static Set<Authority> toDomain(Set<AuthorityDocument> authorityEntities) {
    	 return authorityEntities.stream()
                 .map(authority -> AuthorityBuilder.authority()
                         .name(new AuthorityName(authority.name)).build())
                 .collect(Collectors.toSet());
    }
	
}

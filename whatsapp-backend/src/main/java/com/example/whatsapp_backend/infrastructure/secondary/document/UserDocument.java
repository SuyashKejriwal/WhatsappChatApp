package com.example.whatsapp_backend.infrastructure.secondary.document;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.UserBuilder;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserEmail;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserFirstName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserImageUrl;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserLastName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "whatsapp_user")// MongoDB collection name
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserDocument extends AbstractAuditingEntity<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String id;

    @Field("last_name")
    private String lastName;

    @Field("first_name")
    private String firstName;

    @Field("email")
    private String email;

    @Field("image_url")
    private String imageUrl;

    @Field("public_id")
    private UUID publicId;

    @Field("last_seen")
    private Instant lastSeen;
    
    @Field("conversations")
    private Set<ConversationDocument> conversations = new HashSet<>();

    @Field("authorities")
    private Set<AuthorityDocument> authorities=new HashSet<>();

    public void updateFromUser(User user) {
        this.email = user.getEmail().value();
        this.lastName = user.getLastName().value();
        this.firstName = user.getFirstName().value();
        this.imageUrl = user.getImageUrl().value();
    }
    
    public static UserDocument from(User user) {
    	
        return UserDocument.builder()
                .id(String.valueOf(user.getDbId()) != null ? String.valueOf(user.getDbId()) : null)
                .lastName(user.getLastName().value())
                .firstName(user.getFirstName().value())
                .email(user.getEmail().value())
                .imageUrl(user.getImageUrl() != null ? user.getImageUrl().value() : null)
                .publicId(user.getUserPublicId() != null ? user.getUserPublicId().value() : null)
                .lastSeen(user.getLastSeen())
                .authorities(AuthorityDocument.from(user.getAuthorities()))
                //.conversations(new HashSet<>()) // Replace with appropriate mapping if needed
                .build();
    }

    public static User toDomain(UserDocument userDocument) {
    	 UserBuilder userBuilder = UserBuilder.user();

         if (userDocument.getImageUrl() != null) {
             userBuilder.imageUrl(new UserImageUrl(userDocument.getImageUrl()));
         }

         return userBuilder
                 .email(new UserEmail(userDocument.getEmail()))
                 .lastName(new UserLastName(userDocument.getLastName()))
                 .firstName(new UserFirstName(userDocument.getFirstName()))
                 .authorities(AuthorityDocument.toDomain(userDocument.getAuthorities()))
                 .userPublicId(new UserPublicId(userDocument.getPublicId()))
                 .lastModifiedDate(userDocument.getLastModifiedDate())
                 .createdDate(userDocument.getCreatedDate())
                 .dbId(Long.valueOf(userDocument.getId()))
                 .lastSeen(userDocument.getLastSeen())
                 .build();
    }

    public static Set<UserDocument> from(List<User> users) {
        return users.stream().map(UserDocument::from).collect(Collectors.toSet());
    }

    public static Set<User> toDomain(Set<UserDocument> userDocuments) {
        return userDocuments.stream().map(UserDocument::toDomain).collect(Collectors.toSet());
    }

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		 return id;
	}
}

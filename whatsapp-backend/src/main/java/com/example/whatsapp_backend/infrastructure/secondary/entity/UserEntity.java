package com.example.whatsapp_backend.infrastructure.secondary.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.UuidGenerator;
import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.user.aggregate.User;
import com.example.whatsapp_backend.messaging.domain.user.aggregate.UserBuilder;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserEmail;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserFirstName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserImageUrl;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserLastName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.jpa.AbstractAuditingEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "whatsapp_user")
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity extends AbstractAuditingEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @UuidGenerator
    @Column(name = "public_id", updatable = false)
    private UUID publicId;

    @Column(name = "last_seen")
    private Instant lastSeen = Instant.now();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "user_conversation",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id", referencedColumnName = "id")}
    )
    private Set<ConversationEntity> conversations = new HashSet<>();

    public UserEntity(Long id, String lastName, String firstName, String email, String imageUrl,
                      UUID publicId, Instant lastSeen, Set<AuthorityEntity> authorities,
                      Set<ConversationEntity> conversations) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.lastSeen = lastSeen;
        this.authorities = authorities;
        this.conversations = conversations;
    }

    public UserEntity() {
    }

    public void updateFromUser(User user) {
        this.email = user.getEmail().value();
        this.lastName = user.getLastName().value();
        this.firstName = user.getFirstName().value();
        this.imageUrl = user.getImageUrl().value();
    }

    public static UserEntity from(User user) {
        UserEntityBuilder userEntityBuilder = UserEntityBuilder.userEntity();

        if (user.getImageUrl() != null) {
            userEntityBuilder.imageUrl(user.getImageUrl().value());
        }

        if (user.getUserPublicId() != null) {
            userEntityBuilder.publicId(user.getUserPublicId().value());
        }

        return userEntityBuilder
                .authorities(AuthorityEntity.from(user.getAuthorities()))
                .email(user.getEmail().value())
                .firstName(user.getFirstName().value())
                .lastName(user.getLastName().value())
                .id(user.getDbId())
                .lastSeen(user.getLastSeen())
                .build();
    }

    public static User toDomain(UserEntity userEntity) {
        UserBuilder userBuilder = UserBuilder.user();

        if (userEntity.getImageUrl() != null) {
            userBuilder.imageUrl(new UserImageUrl(userEntity.getImageUrl()));
        }

        return userBuilder
                .email(new UserEmail(userEntity.getEmail()))
                .lastName(new UserLastName(userEntity.getLastName()))
                .firstName(new UserFirstName(userEntity.getFirstName()))
                .authorities(AuthorityEntity.toDomain(userEntity.getAuthorities()))
                .userPublicId(new UserPublicId(userEntity.getPublicId()))
                .lastModifiedDate(userEntity.getLastModifiedDate())
                .createdDate(userEntity.getCreatedDate())
                .dbId(userEntity.getId())
                .lastSeen(userEntity.getLastSeen())
                .build();
    }

    public static Set<UserEntity> from(List<User> users) {
        return users.stream().map(UserEntity::from).collect(Collectors.toSet());
    }

    public static Set<User> toDomain(Set<UserEntity> user) {
        return user.stream().map(UserEntity::toDomain).collect(Collectors.toSet());
    }
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		 return id;
	}
}

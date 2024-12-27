package com.example.whatsapp_backend.messaging.domain.user.aggregate;

import java.time.Instant;
import java.util.Set;

import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.user.vo.UserEmail;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserFirstName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserImageUrl;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserLastName;
import com.example.whatsapp_backend.messaging.domain.user.vo.UserPublicId;
import com.example.whatsapp_backend.shared.error.domain.Assert;

import lombok.Data;

@Builder
@Data
public class User {
	
	private UserLastName lastName;
	
	private UserFirstName firstName;
	
	private UserEmail email;
	
	private UserPublicId userPublicId;
	
	private UserImageUrl imageUrl;
	
	private Instant lastModifiedDate;
	
	private Instant createdDate;
	
	private Instant lastSeen;
	
	private Set<Authority> authorities;
	
	private long dbId;

	public User(UserLastName lastName, UserFirstName firstName, UserEmail userEmail, UserPublicId userPublicId,
			UserImageUrl imageUrl, Instant lastModifiedDate, Instant createdDate, Instant lastSeen,
			Set<Authority> authorities, long dbId) {
		assertMandatoryFields(lastName,firstName,userEmail,authorities);
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = userEmail;
		this.userPublicId = userPublicId;
		this.imageUrl = imageUrl;
		this.lastModifiedDate = lastModifiedDate;
		this.createdDate = createdDate;
		this.lastSeen = lastSeen;
		this.authorities = authorities;
		this.dbId = dbId;
	}
	
	private void assertMandatoryFields(UserLastName lastName, UserFirstName firstName, UserEmail userEmail,Set<Authority> authorities) {
		Assert.notNull("lastName",lastName);
		Assert.notNull("firstName", firstName);
		Assert.notNull("userEmail",userEmail);
		Assert.notNull("authorities", authorities);
	}
	
	public void updateFromUser(User user) {
		this.email=user.email;
		this.lastName=user.lastName;
		this.firstName=user.firstName;
		this.imageUrl=user.imageUrl;
	}
	
	public void initFieldForSignUp() {
		this.lastSeen=Instant.now();
	}
}

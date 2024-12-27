package com.example.whatsapp_backend.messaging.domain.user.vo;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record UserEmail(String value) {

	 public UserEmail {
	        Assert.field(value, value).maxLength(255);
	    }
}

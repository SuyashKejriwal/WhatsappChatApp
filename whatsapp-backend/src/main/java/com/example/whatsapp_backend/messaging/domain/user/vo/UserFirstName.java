package com.example.whatsapp_backend.messaging.domain.user.vo;

import com.example.whatsapp_backend.shared.error.domain.Assert;

public record UserFirstName(String value) {
	
	public UserFirstName {
        Assert.field(value, value).maxLength(255);
    }

}

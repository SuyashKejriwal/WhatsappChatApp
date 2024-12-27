package com.example.whatsapp_backend.messaging.domain.user.aggregate;


import org.jilt.Builder;

import com.example.whatsapp_backend.messaging.domain.user.vo.AuthorityName;
import com.example.whatsapp_backend.shared.error.domain.Assert;

import lombok.Data;

@Builder
@Data
public class Authority {
	
	private AuthorityName name;
	
	public Authority(AuthorityName name) {
		Assert.notNull("name", name);
		this.name=name;
	}

}

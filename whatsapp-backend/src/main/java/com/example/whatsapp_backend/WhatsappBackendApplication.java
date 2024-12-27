package com.example.whatsapp_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class WhatsappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappBackendApplication.class, args);
	}

}

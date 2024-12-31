package com.example.whatsapp_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class WhatsappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappBackendApplication.class, args);
	}

}

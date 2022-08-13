package com.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.social.service.ChatService;

@SpringBootApplication
public class SocialMediaApplication implements CommandLineRunner{

	@Autowired
	private ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
		System.out.println("This application is running in port 8081");
	}

	@Override
	public void run(String... args) throws Exception {
		// chatService.updateId();
		
	}

}

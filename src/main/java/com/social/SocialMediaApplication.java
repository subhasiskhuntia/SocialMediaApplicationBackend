package com.social;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.social.dao.FriendDao;
import com.social.dao.PostDao;
import com.social.dao.UserDao;
import com.social.service.ChatService;

@SpringBootApplication
public class SocialMediaApplication implements CommandLineRunner{

	@Autowired
	private ChatService chatService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private PostDao postDao;

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
		System.out.println("This application is running in port 8081");
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public JavaMailSender mailSender() {
		return new JavaMailSenderImpl();
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// chatService.updateId();
		
		// friendDao.getFriends("subhasiskhuntia506@gmail.com").forEach(a->System.out.println(a));
		// List<Long> ids=friendDao.getFriends("subhasiskhuntia506@gmail.com");
		// System.out.println( userDao.findAllById(ids));
		// postDao.getFriendsPost(1L).forEach(a->System.out.println(a.get("title")));
		// System.out.println(userDao.findByUsername("subhasiskhuntia506@gmail.com").getUserLikes());
		// System.out.println(postDao.findById(10L).orElse(null).getLikes());
	}

}

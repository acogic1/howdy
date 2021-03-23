package com.example.howdynewsfeed;

import com.example.howdynewsfeed.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.howdynewsfeed.repository.UserRepository;

@SpringBootApplication
public class HowdyNewsfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowdyNewsfeedApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository){
		return (args -> {
			userRepository.save(new User(1L,"acogic1"));
			userRepository.save(new User(2L,"dsabic1"));
			userRepository.save(new User(3L,"vherceglija1"));
		});
	}

}

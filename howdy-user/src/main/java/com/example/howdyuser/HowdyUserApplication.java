package com.example.howdyuser;

//import com.example.howdyuser.User.User;
import com.example.howdyuser.User.User;
import com.example.howdyuser.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HowdyUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowdyUserApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository){
		return (args -> {
			userRepository.save(new User(1L,"acogic1@etf.unsa.ba","acogic1","11111111","proba"));
			userRepository.save(new User(2L,"dsabic1@etf.unsa.ba","dsabic1","22222222","proba"));
			userRepository.save(new User(3L,"vherceglija1@etf.unsa.ba","vherceglija1","33333333","proba"));
		});
	}

}

package com.example.howdyMessagesFollowersFollowing;

import com.example.howdyMessagesFollowersFollowing.Message.MessageRepository;
import com.example.howdyMessagesFollowersFollowing.Message.Message;
import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.example.howdyMessagesFollowersFollowing.Subscription.SubscriptionRepository;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class HowdyMessagesFollowersFollowingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowdyMessagesFollowersFollowingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository){
		return (args -> {
			userRepository.save(new User(1L,"acogic1"));
			userRepository.save(new User(2L,"dsabic1"));
			userRepository.save(new User(3L,"vherceglija1"));
		});
	}

	@Bean
	public CommandLineRunner demo1(MessageRepository mesageRepository){
		return (args -> {
			Calendar cal=Calendar.getInstance();
			Date date=cal.getTime();
			User user1=new User(1L,"acogic1");
			User user2=new User(2L,"dsabic1");
			User user3=new User(3L,"vherceglija1");
			mesageRepository.save(new Message(1L,user1,user2, date,"prva poruka"));
			mesageRepository.save(new Message(2L,user1,user2, date,"sada malo nastavka"));
			mesageRepository.save(new Message(3L,user2,user1, date,"kao da ovo radi"));
			mesageRepository.save(new Message(4L,user1,user3, date,"pozz"));
			mesageRepository.save(new Message(5L,user3,user2, date,"xyyx"));
			mesageRepository.save(new Message(6L,user2,user3, date,"neki tekst"));
		});
	}

	@Bean
	public CommandLineRunner demo2(SubscriptionRepository subscriptionRepository){
		return (args -> {
			User user1=new User(1L,"acogic1");
			User user2=new User(2L,"dsabic1");
			User user3=new User(3L,"vherceglija1");
			subscriptionRepository.save(new Subscription(1L,user1,user2));
			subscriptionRepository.save(new Subscription(2L,user1,user3));
			subscriptionRepository.save(new Subscription(3L,user3,user2));
			subscriptionRepository.save(new Subscription(user2,user1));

		});
	}
}

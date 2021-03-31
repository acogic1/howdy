package com.example.howdynewsfeed;

import com.example.howdynewsfeed.models.Comment;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.models.Reaction;
import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.repository.CommentRepository;
import com.example.howdynewsfeed.repository.PostRepository;
import com.example.howdynewsfeed.repository.ReactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import com.example.howdynewsfeed.repository.UserRepository;


@SpringBootApplication
@EnableEurekaClient
public class HowdyNewsfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(HowdyNewsfeedApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo1(UserRepository userRepository){
		return (args -> {
			userRepository.save(new User(1L,"acogic1"));
			userRepository.save(new User(2L,"dsabic1"));
			userRepository.save(new User(3L,"vherceglija1"));
			userRepository.save(new User(4L,"ghostUser"));

		});
	}

	@Bean
	public CommandLineRunner demo2(PostRepository postRepository){
		return (args -> {
			User user1 = new User(1L,"acogic1");
			User user2 = new User(2L,"dsabic1");
			User user3 = new User(3L,"vherceglija1");

			postRepository.save(new Post(1L,user2,"Neki content"));
			postRepository.save(new Post(2L,user1, "Ovo je neki post"));
			postRepository.save(new Post(3L,user3,"Post, post...."));
			postRepository.save(new Post(4L,user2,"Post od usera 2"));
			postRepository.save(new Post(5L,user2,"Opet post od usera 2"));
			postRepository.save(new Post(6L,user3,"Post od usera 3"));

		});
	}

	@Bean
	public CommandLineRunner demo3(CommentRepository commentRepository){
		return (args -> {

			User user1 = new User(1L,"acogic1");
			User user2 = new User(2L,"dsabic1");
			User user3 = new User(3L,"vherceglija1");

			Post post1 = new Post(1L,user2,"Neki content");
			Post post2 = new Post(2L,user1,"Ovo je neki post");
			Post post3 = new Post(3L,user3,"Post, post...");
			Post post4 = new Post(4L,user2,"Post od usera 2");
			Post post5 = new Post(5L,user2,"Opet post od usera 2");
			Post post6 = new Post(6L,user3,"Post od usera 3");

			commentRepository.save(new Comment(1L,user2,post1,"Prvi komentar!"));
			commentRepository.save(new Comment(2L,user1,post2,"Neki komentar :)"));
			commentRepository.save(new Comment(3L,user3,post3,"Komentar hehe"));
			commentRepository.save(new Comment(4L,user2,post4,"Prvi!"));
			commentRepository.save(new Comment(5L,user2,post4,"komentar!"));
			commentRepository.save(new Comment(6L,user3,post5,"Kooom!"));

		});
	}

	@Bean
	public CommandLineRunner demo4(ReactionRepository reactionRepository){

		return (args -> {

			User user1 = new User(1L,"acogic1");
			User user2 = new User(2L,"dsabic1");
			User user3 = new User(3L,"vherceglija1");

			Post post1 = new Post(1L,user2,"Neki content");
			Post post2 = new Post(user1,"Ovo je neki post");
			Post post3 = new Post(3L,user3,"Post, post...");
			Post post4 = new Post(4L,user2,"Post od usera 2");
			Post post5 = new Post(5L,user2,"Opet post od usera 2");
			Post post6 = new Post(6L,user3,"Post od usera 3");

			reactionRepository.save(new Reaction(1L,user2,post1, Reaction.TypeReaction.Like));
			reactionRepository.save(new Reaction(2L,user1,post2, Reaction.TypeReaction.FakeNews ));
			reactionRepository.save(new Reaction(3L,user3,post3, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(4L,user2,post3, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(5L,user3,post4, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(6L,user3,post5, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(7L,user3,post6, Reaction.TypeReaction.Sad));

		});
	}

}

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
import org.springframework.context.annotation.Bean;
import com.example.howdynewsfeed.repository.UserRepository;
import org.springframework.data.repository.query.parser.Part;

import java.lang.reflect.Type;

@SpringBootApplication
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
			postRepository.save(new Post(1L,2L,"Neki content"));
			postRepository.save(new Post(2L,1L, "Ovo je neki post"));
			postRepository.save(new Post(3L,3L,"Post, post...."));
			postRepository.save(new Post(4L,2L,"Post od usera 2"));
			postRepository.save(new Post(5L,2L,"Opet post od usera 2"));
			postRepository.save(new Post(6L,3L,"Post od usera 3"));

		});
	}

	@Bean
	public CommandLineRunner demo3(CommentRepository commentRepository){
		return (args -> {
			commentRepository.save(new Comment(1L,2L,1L,"Prvi komentar!"));
			commentRepository.save(new Comment(2L,1L,2L,"Neki komentar :)"));
			commentRepository.save(new Comment(3L,3L,3L,"Komentar hehe"));
			commentRepository.save(new Comment(4L,2L,4L,"Prvi!"));
			commentRepository.save(new Comment(5L,2L,4L,"komentar!"));
			commentRepository.save(new Comment(6L,3L,5L,"Kooom!"));

		});
	}

	@Bean
	public CommandLineRunner demo4(ReactionRepository reactionRepository){

		return (args -> {
			reactionRepository.save(new Reaction(1L,2L,1L, Reaction.TypeReaction.Like));
			reactionRepository.save(new Reaction(2L,1L,2L, Reaction.TypeReaction.FakeNews ));
			reactionRepository.save(new Reaction(3L,3L,3L, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(4L,2L,3L, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(5L,3L,4L, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(6L,3L,5L, Reaction.TypeReaction.Sad));
			reactionRepository.save(new Reaction(7L,3L,6L, Reaction.TypeReaction.Sad));

		});
	}

}

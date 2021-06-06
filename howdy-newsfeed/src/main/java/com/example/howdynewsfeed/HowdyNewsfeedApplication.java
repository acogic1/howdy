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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.example.howdynewsfeed.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//@EnableZuulProxy
public class HowdyNewsfeedApplication {

    /*@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(HowdyNewsfeedApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo1(UserRepository userRepository){
		return (args -> {
			userRepository.save(new User(1L,"acogic1","11111111"));
			userRepository.save(new User(2L,"dsabic1","22222222"));
			userRepository.save(new User(3L,"vherceglija1","33333333"));
			userRepository.save(new User(4L,"iprazina","44444444"));
			userRepository.save(new User(5L,"David Beckham","david"));
			userRepository.save(new User(6L,"Dino Merlin","dino"));
			userRepository.save(new User(7L,"Abraham Lincoln","abraham"));
			userRepository.save(new User(8L,"zdravkocolic","zdravko"));


		});
	}

	@Bean
	public CommandLineRunner demo2(PostRepository postRepository){
		return (args -> {
			User user1 = new User(1L,"acogic1","11111111");
			User user2 = new User(2L,"dsabic1", "22222222");
			User user3 = new User(3L,"vherceglija1","33333333");
			User user4 = new User(4L,"iprazina","44444444");
			User user5 = new User(5L,"David Beckham","david");
			User user6 = new User(6L,"Dino Merlin","dino");
			User user7 = new User(7L,"Abraham Lincoln","abraham");
			User user8 = new User(8L,"zdravkocolic","zdravko");

			postRepository.save(new Post(1L,user2,"Ćao raja, registrovao sam se na ovoj odličnoj društvenoj mreži!!"));
			postRepository.save(new Post(2L,user1, "Ja sam Adem, ovo je moj prvi status :) "));
			postRepository.save(new Post(3L,user3,"Zdravo, ja sam Vedad, odustao sam od projekta :P "));
			postRepository.save(new Post(4L,user2,"Evo jedan moj status."));
			postRepository.save(new Post(5L,user2,"Volim slušati Zdravka Čolića."));
			postRepository.save(new Post(6L,user3,"Položio sam danas IM2"));
			postRepository.save(new Post(7L,user1,"Ima li neko skriptu za RM?"));
			postRepository.save(new Post(8L,user1, "Oženio sam se danas."));
			postRepository.save(new Post(9L,user3,"Tužan sam. "));
			postRepository.save(new Post(10L,user4,"Ocjenjujem projekte."));
			postRepository.save(new Post(11L,user5,"Zabio sam danas 3 gola"));
			postRepository.save(new Post(12L,user5,"Povrijedio sam se na treningu."));

		});
	}

	@Bean
	public CommandLineRunner demo3(CommentRepository commentRepository){
		return (args -> {

			User user1 = new User(1L,"acogic1","11111111");
			User user2 = new User(2L,"dsabic1", "22222222");
			User user3 = new User(3L,"vherceglija1","33333333");
			User user4 = new User(4L,"iprazina","44444444");
			User user5 = new User(5L,"David Beckham","david");
			User user6 = new User(6L,"Dino Merlin","dino");
			User user7 = new User(7L,"Abraham Lincoln","abraham");
			User user8 = new User(8L,"zdravkocolic","zdravko");

			Post post1 = new Post(1L,user2,"Neki content");
			Post post2 = new Post(2L,user1,"Ovo je neki post");
			Post post3 = new Post(3L,user3,"Post, post...");
			Post post4 = new Post(4L,user2,"Post od usera 2");
			Post post5 = new Post(5L,user2,"Opet post od usera 2");
			Post post6 = new Post(6L,user3,"Post od usera 3");
			Post post7 = new Post(1L,user2,"Neki content");
			Post post8 = new Post(2L,user1,"Ovo je neki post");
			Post post9 = new Post(3L,user3,"Post, post...");
			Post post10 = new Post(4L,user2,"Post od usera 2");
			Post post11 = new Post(5L,user2,"Opet post od usera 2");
			Post post12 = new Post(6L,user3,"Post od usera 3");

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

			User user1 = new User(1L,"acogic1","11111111");
			User user2 = new User(2L,"dsabic1", "22222222");
			User user3 = new User(3L,"vherceglija1","33333333");

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
	}*/

}

package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.*;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.repository.ReactionRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class AccessingDataMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMysqlApplication.class, args);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

    }

    @Bean
    public CommandLineRunner demo(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, ReactionRepository reactionRepository) {
        return (args -> {
            User vedad = userRepository.save(new User("vedad@mail.com", "vedad"));
            User dzejlan = userRepository.save(new User("dzejlan@mail.com", "dzejlan"));
            User adem = userRepository.save(new User("adem@mail.com", "adem"));
            Post vedadovPost = postRepository.save(new Post("Veadov post", vedad));
            Post dzejlanovPost = postRepository.save(new Post("Dzejlanov post", dzejlan));
            Post ademovPost = postRepository.save(new Post("post3", adem));
            commentRepository.save(new Comment(vedad, "Samo napirejd! Kako su djeca?", dzejlanovPost));
            commentRepository.save(new Comment(dzejlan, "OOOOOO, gdje si to kupio?", vedadovPost));
            commentRepository.save(new Comment(adem, "Dobro došao u klub!!", vedadovPost));
            reactionRepository.save(new Reaction(vedad, ademovPost, Reaction.ReactionType.HAHA));

        });
    }

}

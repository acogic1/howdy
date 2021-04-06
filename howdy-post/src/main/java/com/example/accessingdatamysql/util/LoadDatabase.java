package com.example.accessingdatamysql.util;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.Reaction;
import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.repository.ReactionRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PostRepository postRepository, CommentRepository commentRepository, ReactionRepository reactionRepository, UserRepository userRepository) {

        return args -> {
            log.info("Preloading " );
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
            reactionRepository.save(new Reaction(vedad, ademovPost, Reaction.ReactionType.HAHA));
            reactionRepository.save(new Reaction(vedad, ademovPost, Reaction.ReactionType.HAHA));

        };
    }
}

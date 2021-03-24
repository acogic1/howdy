package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMysqlApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PostRepository postRepository) {
        return (args -> {
            postRepository.save(new Post("post1", 1));
            postRepository.save(new Post("post2", 2));
            postRepository.save(new Post("post3", 3));
        });
    }

}

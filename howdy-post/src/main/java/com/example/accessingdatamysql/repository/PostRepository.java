package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByUser(Long id);

}
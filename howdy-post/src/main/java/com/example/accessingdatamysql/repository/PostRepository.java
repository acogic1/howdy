package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}
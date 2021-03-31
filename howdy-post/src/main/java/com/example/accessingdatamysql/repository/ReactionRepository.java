package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface ReactionRepository extends CrudRepository<Post, Long> {
}

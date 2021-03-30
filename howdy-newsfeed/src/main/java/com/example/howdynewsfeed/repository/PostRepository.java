package com.example.howdynewsfeed.repository;

import com.example.howdynewsfeed.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post,Long> {
    Optional<Post> findById(Long id);
}

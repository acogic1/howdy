package com.example.howdynewsfeed.repository;

import com.example.howdynewsfeed.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
}

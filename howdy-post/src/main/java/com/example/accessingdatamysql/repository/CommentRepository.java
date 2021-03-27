package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}

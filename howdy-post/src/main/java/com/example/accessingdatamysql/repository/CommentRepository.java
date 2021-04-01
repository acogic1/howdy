package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

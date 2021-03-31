package com.example.howdynewsfeed.services;

import com.example.howdynewsfeed.models.Comment;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsById(Long postId) {
        List<Comment> allComments = getComments();
        List<Comment> postsComments = new ArrayList<Comment>();
        for (Comment comment : allComments) {
            if (comment.getPostId().equals(postId)) {
                postsComments.add(comment);
            }
        }

        return postsComments;
    }

}

package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getComment(Long commentId){
        return commentRepository.findById(commentId);
    }
//    public void deletePost(Long postId){
//
//        postRepository.deleteById(postId);
//
//    }

    public Comment addComment(Long userId, Long postid, String content) {
        User user = userRepository.getOne(userId);
        Post post = postRepository.getOne(postid);

        return commentRepository.save(new Comment(user, content, post));

    }
}


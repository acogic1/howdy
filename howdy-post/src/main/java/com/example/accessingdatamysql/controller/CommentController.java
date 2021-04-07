package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.model.CommentRequest;
import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.PostRequest;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.service.CommentService;
import com.example.accessingdatamysql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/all/{id}")
    List<Comment> getAllCommentsPost(@PathVariable Long id) {
        return commentService.getCommentPost(id);
    }

    @GetMapping("/{id}")
    Optional<Comment> getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @DeleteMapping("/{id}")
    void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

    @PostMapping("/add/{userid}/{postid}")
    Comment addPost(@RequestBody CommentRequest commentRequest, @PathVariable Long userid, @PathVariable Long postid) {
        return commentService.addComment(userid, postid, commentRequest.getContent());
    }

}

package com.example.howdynewsfeed.controllers;

import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Comment;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.repository.CommentRepository;
import com.example.howdynewsfeed.services.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {

    //private final CommentRepository commentRepository;
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService=commentService;
    }

    @GetMapping("comments")
    Iterable<Comment> all(){
        try {
            return this.commentService.getComments();
        }
        catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @GetMapping("comment/{id}")
    Comment one(@PathVariable Long id){
        return commentService.getCommentById(id)
                .orElseThrow(() -> new NotFoundException("comment",id));
    }

    @GetMapping("comments/{postId}")
    List<Comment> all(@PathVariable Long postId) {
        try {
            return commentService.getCommentsById(postId);
        }
        catch (Exception e) {
            throw new NotFoundException("Users posts are not found",postId);
        }
    }
}

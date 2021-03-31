package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {
    //TODO: NE BI SE TREBAO USER ID UNOSITI NEGO GLEDATI KO JE PRIJAVLJEN

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

//    @PutMapping(path = "/add/{id}") // Map ONLY POST Requests
//    public @ResponseBody
//    String addComment(@RequestParam String text, @RequestParam long postId, @PathVariable Long id) {
//        commentRepository.findById(id).map(comment1 -> {
//            comment1.setText(text);
//            return commentRepository.save(comment1);
//        }).orElseGet(() -> {
//            Comment c = new Comment();
//
//            postRepository.findById(postId).map(post1 -> {
//                c.setUserid(post1.getUserId());
//                c.setText(text);
//                c.setPost(post1);
//
//                return commentRepository.save(c);
//
//            });
//            return commentRepository.save(c);
//
//        });
//        return "saved";
//    }
}

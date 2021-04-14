package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.model.CommentRequest;
import com.example.accessingdatamysql.model.Reaction;
import com.example.accessingdatamysql.service.CommentService;
import com.example.accessingdatamysql.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/all")
    List<Reaction> getAllReactions() {
        return reactionService.getAllReactions();
    }

    @GetMapping("/all/{id}")
    List<Reaction> getAllReactionsHahaPost(@PathVariable Long id) {
        return reactionService.getReactionHahaPost(id);
    }
    @GetMapping("/all/{id}")
    List<Reaction> getAllReactionsLikePost(@PathVariable Long id) {
        return reactionService.getReactionLikePost(id);
    }

    @GetMapping("/all/{id}")
    List<Reaction> getAllReactionsDislikePost(@PathVariable Long id) {
        return reactionService.getReactionDislikePost(id);
    }


    @GetMapping("/all/{id}")
    List<Reaction> getAllReactionsSadPost(@PathVariable Long id) {
        return reactionService.getReactionSadPost(id);
    }




    @DeleteMapping("/{id}")
    void deletePost(@PathVariable Long id) {
        reactionService.deleteReaction(id);
    }

    @PostMapping("/add/{userid}/{postid}")
    Reaction addReaction(@RequestBody CommentRequest commentRequest, @PathVariable Long userid, @PathVariable Long postid) {
        return reactionService.addReaction(userid, postid, commentRequest.getContent());
    }
}

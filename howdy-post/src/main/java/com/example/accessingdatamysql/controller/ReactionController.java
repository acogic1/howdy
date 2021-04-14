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
    List<Reaction> getAllReactionsPost(@PathVariable Long id, int r) {
        if (r == 0) {
            return reactionService.getAllReactionsPost(id, Reaction.ReactionType.HAHA);
        }
        if (r == 1) {
            return reactionService.getAllReactionsPost(id, Reaction.ReactionType.DISLIKE);
        }
        if (r == 2) {
            return reactionService.getAllReactionsPost(id, Reaction.ReactionType.SAD);
        } else {
            return reactionService.getAllReactionsPost(id, Reaction.ReactionType.LIKE);
        }

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

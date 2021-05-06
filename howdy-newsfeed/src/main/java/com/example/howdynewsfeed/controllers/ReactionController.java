package com.example.howdynewsfeed.controllers;

import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Reaction;
import com.example.howdynewsfeed.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ReactionController {

    @Autowired
    private RestTemplate restTemplate;

    //private final ReactionRepository reactionRepository;
    private final ReactionService reactionService;

    ReactionController (ReactionService reactionService) {

        this.reactionService=reactionService;
    }

    @GetMapping("reactions")
    Iterable<Reaction> all(){
        try {
            return this.reactionService.getReactions();
        }
        catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @GetMapping("reaction/{id}")
    Reaction one(@PathVariable Long id){
        return reactionService.getReactionById(id)
                .orElseThrow(() -> new NotFoundException("reaction",id));
    }

    @GetMapping("reactions/{postId}")
    List<Reaction> all(@PathVariable Long postId) {
        try {
            return reactionService.getReactionsById(postId);
        }
        catch (Exception e) {
            throw new NotFoundException("reactions on post",postId);
        }
    }
}

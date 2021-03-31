package com.example.howdynewsfeed.services;

import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.models.Reaction;
import com.example.howdynewsfeed.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService (ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public List<Reaction> getReactions() {
        return (List<Reaction>) reactionRepository.findAll();
    }

    public List<Reaction> getReactionsById(Long postId) {
        try {
            List<Reaction> allReactions = getReactions();
            List<Reaction> postsReactions = new ArrayList<Reaction>();
            for (Reaction reaction : allReactions) {
                if (reaction.getPostId().getId().equals(postId)) {
                    postsReactions.add(reaction);
                }
            }
            if(postsReactions.isEmpty()) throw new NotFoundException("reactions on post",postId);
            return postsReactions;
        }
        catch (Exception e) {
            throw new NotFoundException("reactions on post",postId);
        }
    }

    public Optional<Reaction> getReactionById(Long id) {
        try {
            return reactionRepository.findById(id);
        }
        catch (Exception e) {
            throw new NotFoundException("reaction",id);
        }
    }


}

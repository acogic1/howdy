package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.model.Comment;
import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.Reaction;
import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.repository.ReactionRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(PostRepository postRepository, UserRepository userRepository, ReactionRepository reactionRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.reactionRepository = reactionRepository;
    }


    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

//    public Optional<Post> getPost(Long postId){
//        return postRepository.findById(postId);
//    }
//    public void deletePost(Long postId){
//
//        postRepository.deleteById(postId);
//
//    }
    public void deleteReaction(Long reactionId) {
        reactionRepository.deleteById(reactionId);
    }
    public Reaction addReaction(Long userId, Long postid, String content) {
        //TODO: PROVJERIT ŠTA AKO NEMA KOMENTARA I USERA
        User user = userRepository.getOne(userId);
        Post post = postRepository.getOne(postid);
        return reactionRepository.save(new Reaction(user, post, Reaction.ReactionType.HAHA));
    }
}

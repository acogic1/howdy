package com.example.howdynewsfeed.services;

import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public List<Post> getPostsById(Long userId) {
        List<Post> allPosts = getPosts();
        List<Post> usersPosts = new ArrayList<Post>();
        for (Post post : allPosts) {
            if (post.getUserId().equals(userId)) {
                usersPosts.add(post);
            }
        }

        return usersPosts;
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}

package com.example.howdynewsfeed.controllers;

import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/")
public class PostController {

    @Autowired
    private RestTemplate restTemplate;

    //private final PostRepository postRepository;
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    List<Post> all(){
        try {
            return postService.getPosts();
        }
        catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @GetMapping("postFollowing/{userId}")
    List<User> getFollowingFromUser (@PathVariable("userId") Long id) {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("localhost:8082/subscriptions/following/" + id, User[].class);

        List<User> users = Arrays.asList(responseEntity.getBody());

        return users;
    }

    @GetMapping("post/{id}")
    Post one(@PathVariable Long id){
        return postService.getPostById(id)
                .orElseThrow(() -> new NotFoundException("post",id));
    }

    @GetMapping("posts/{userId}")
    List<Post> all(@PathVariable Long userId) {
        try {
            return postService.getPostsById(userId);
        }
        catch (Exception e) {
            throw new NotFoundException("posts by user",userId);
        }
    }
}

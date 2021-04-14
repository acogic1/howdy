package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.PostRequest;
import com.example.accessingdatamysql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    Optional<Post> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }



    @DeleteMapping("/{id}")
    void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

    @PostMapping("/add/{id}")
    Post addPost(@RequestBody PostRequest postRequest, @PathVariable Long id) {
        return postService.addPost(id, postRequest.getContent());
    }


}
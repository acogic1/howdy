package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.model.Post;
import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.repository.CommentRepository;
import com.example.accessingdatamysql.repository.PostRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsFromUser(Long userId) {
        return postRepository.findPostByUser_Id(userId);
    }

    public List<Post> getAllPostsFromUsers(List<Long> users) {
        List<Post> posts = new ArrayList<Post>();
        for (Long u : users) {
            posts.addAll(postRepository.findPostByUser_Id(u));
        }
        return posts;
    }


    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }


    public void deletePost(Long postId) {

        postRepository.deleteById(postId);

    }

    public Post addPost(Long userId, String content) {
        User user = userRepository.getOne(userId);
        return postRepository.save(new Post(content, user));
    }
}

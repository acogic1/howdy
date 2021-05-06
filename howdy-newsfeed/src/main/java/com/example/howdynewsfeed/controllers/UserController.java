package com.example.howdynewsfeed.controllers;

import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @GetMapping("users")
    Iterable<User> all(){
        try {
            return this.userRepository.findAll();
        }
        catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @GetMapping("user/{id}")
    User one(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user",id));
    }
}

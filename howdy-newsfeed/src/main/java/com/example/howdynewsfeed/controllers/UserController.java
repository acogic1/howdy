package com.example.howdynewsfeed.controllers;

import com.example.howdynewsfeed.Exceptions.BadRequestException;
import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.repository.UserRepository;
import com.example.howdynewsfeed.services.PostService;
import com.example.howdynewsfeed.services.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    //private final UserRepository userRepository;
    private final UserService userService;


    UserController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping("users")
    List<User> all(){
        return userService.getUsers();
    }

    @GetMapping("userId/{id}")
    User one(@PathVariable Long id){
        try {
            return userService.getUserById(id);
        }catch (Exception e) {
                throw new NotFoundException("post", id);
        }
    }

    @GetMapping("user/{username}")
    Long GetIdByUser(@PathVariable String username){
        try {
            return userService.findIdByUsername(username);
        }
        catch (Exception e) {
            throw new NotFoundException("user",username);
        }
    }

    @PostMapping("/users")
    User Add( @RequestBody User newUser) throws URISyntaxException {

        try {
            return  userService.Add(newUser);
        }
        catch (ConstraintViolationException e){
            throw new BadRequestException(e.getMessage());
            //throw e;
        }
        catch (NotFoundException e){
            throw e;
        }

        catch (Exception e){
            throw new BadRequestException(e.getMessage());
            //throw new InternalServerException();
        }

    }
}

package com.example.howdyMessagesFollowersFollowing.User;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    //private final UserRepository userRepository;
    @Autowired
    UserService userService;

    /*UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }*/

    @GetMapping("/users")
    List<User> all(){
        try {
            return  userService.GetAll();
        }
        catch (Exception e){
            throw new InternalServerException();
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

    @GetMapping("/users/{id}")
    User GetById(@PathVariable Long id){
        try {
            return userService.GetById(id);
        }
        catch (NotFoundException ex){
            throw ex;
        }
        catch (Exception ex){
            throw new InternalServerException();
        }

    }

    @PutMapping("/users/{id}")
    User Update(@RequestBody User newUser, @PathVariable Long id){
        try {
            return userService.Update(newUser, id);
        }
        catch (ConstraintViolationException ex) {
            throw new BadRequestException(ex.getMessage());
        }
        catch (NotFoundException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }

    @DeleteMapping("/users/{id}")
    void Delete(@PathVariable Long id) {
        try {
            userService.Delete(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("user", id);
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }
}

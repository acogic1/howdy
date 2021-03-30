package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;


    @GetMapping("/subscriptions")
    List<Subscription> GetAll(){
        try {
            return  subscriptionService.GetAll();
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @PostMapping("/subscriptions")
    Subscription Add(@RequestBody Subscription newSubscription)  {

        try {
            return  subscriptionService.Add(newSubscription);
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

    @GetMapping("/subscriptions/{id}")
    Subscription GetById(@PathVariable Long id){
        try {
            return subscriptionService.GetById(id);
        }
        catch (NotFoundException ex){
            throw ex;
        }
        catch (Exception ex){
            throw new InternalServerException();
        }
    }

    @GetMapping("/subscriptions/followers/{id}")
    List<User> GetFollowersByUser(@PathVariable Long id){
        return subscriptionService.GetFollowersByUser(id);
    }

    @GetMapping("/subscriptions/following/{id}")
    List<User> GetFollowingByUser(@PathVariable Long id){
        return subscriptionService.GetFollowingByUser(id);
    }

    @PutMapping("/subscriptions/{id}")
    Subscription Update(@RequestBody Subscription newSubscription, @PathVariable Long id){
        try {
            return subscriptionService.Update(newSubscription, id);
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

    @DeleteMapping("/subscriptions/{id}")
    void Delete(@PathVariable Long id) {
        try {
            subscriptionService.Delete(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("user", id);
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }
}

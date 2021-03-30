package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.example.howdyMessagesFollowersFollowing.Subscription.SubscriptionRepository;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;


    @GetMapping("/messages")
    List<Message> GetAll() {
        try {
            return messageService.GetAll();
        } catch (Exception e) {
            throw new InternalServerException();
        }

    }

    @PostMapping("/messages")
    Message Add(@RequestBody Message newMessage) {

        try {
            return  messageService.Add(newMessage);
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

    @GetMapping("/messages/{id}")
    Message GetById(@PathVariable Long id) {
        try {
            return messageService.GetById(id);
        }
        catch (NotFoundException ex){
            throw ex;
        }
        catch (Exception ex){
            throw new InternalServerException();
        }
    }

    @GetMapping("/messages/inbox/{id}")
    List<User> GetInboxById(@PathVariable Long id){
        return messageService.GetInboxById(id);
    }

    @GetMapping("/messages/conversation/{user1}/{user2}")
    List<Message> GetConversation(@PathVariable Long user1,@PathVariable Long user2){
        return messageService.GetConversation(user1,user2);
    }

    @PutMapping("/messages/{id}")
    Message Update(@RequestBody Message newMessage, @PathVariable Long id) {
        try {
            return messageService.Update(newMessage, id);
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

    @DeleteMapping("/messages/{id}")
    void Delete(@PathVariable Long id) {
        try {
            messageService.Delete(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("user", id);
        }
        catch (Exception ex) {
            throw new InternalServerException();
        }
    }
}

package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.example.howdyMessagesFollowersFollowing.Subscription.SubscriptionRepository;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import com.grpc.SystemEvents;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;


    @GetMapping("/messages")
    List<Message> GetAll() {
        try {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "GET", "Messages", true, 200);
            return messageService.GetAll();
        } catch (Exception e) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "GET", "Messages", false, 500);
            throw new InternalServerException();
        }

    }

    @PostMapping("/messages")
    Message Add(@RequestBody Message newMessage) {

        try {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "POST", "Messages", true, 201);
            return  messageService.Add(newMessage);
        }
        catch (ConstraintViolationException e){
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "POST", "Messages", false, 400);
            throw new BadRequestException(e.getMessage());
            //throw e;
        }
        catch (NotFoundException e){
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "POST", "Messages", false, 404);
            throw e;
        }

        catch (Exception e){
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "POST", "Messages", false, 500);
            throw new BadRequestException(e.getMessage());
            //throw new InternalServerException();
        }

    }

    @GetMapping("/messages/{id}")
    Message GetById(@PathVariable Long id) {
        try {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "GET", "Messages", true, 200);
            return messageService.GetById(id);
        }
        catch (NotFoundException ex){
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "GET", "Messages", false, 404);
            throw ex;
        }
        catch (Exception ex){
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "GET", "Messages", false, 500);
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
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "PUT", "Messages", true, 200);
            return messageService.Update(newMessage, id);
        }
        catch (ConstraintViolationException ex) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "PUT", "Messages", false, 400);
            throw new BadRequestException(ex.getMessage());
        }
        catch (NotFoundException ex) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "PUT", "Messages", false, 404);
            throw ex;
        }
        catch (Exception ex) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "PUT", "Messages", false, 500);
            throw new InternalServerException();
        }
    }

    @DeleteMapping("/messages/{id}")
    void Delete(@PathVariable Long id) {
        try {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "DELETE", "Messages", true, 204);
            messageService.Delete(id);
        }
        catch (EmptyResultDataAccessException ex) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "DELETE", "Messages", false, 404);
            throw new NotFoundException("user", id);
        }
        catch (Exception ex) {
            LogActivity(new Date().toString(), "MessagesFollowersFollowingMicroservice", "Default", "DELETE", "Messages", false, 500);
            throw new InternalServerException();
        }
    }

    private void LogActivity(String eventTimeStamp, String microservice, String user,
                             String action, String resourceName, Boolean success, Integer responseCode) {
        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                    .usePlaintext()
                    .build();

            com.grpc.SystemEventServiceGrpc.SystemEventServiceBlockingStub stub =
                    com.grpc.SystemEventServiceGrpc.newBlockingStub(channel);

            SystemEvents.SystemEventResponse response = stub.logSystemEvent(SystemEvents.SystemEventRequest.newBuilder()
                    .setEventTimeStamp(eventTimeStamp)
                    .setMicroservice(microservice)
                    .setUser(user)
                    .setAction(action)
                    .setResourceName(resourceName)
                    .setSuccess(success)
                    .setResponseCode(responseCode)
                    .build());

            channel.shutdown();
        }
        catch (Exception ex) {

            System.out.println("greska");
        }
    }
}

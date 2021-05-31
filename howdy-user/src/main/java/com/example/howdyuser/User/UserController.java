package com.example.howdyuser.User;


import com.example.howdyuser.ExceptionClasses.BadRequestException;
import com.example.howdyuser.ExceptionClasses.InternalServerException;
import com.example.howdyuser.ExceptionClasses.NotFoundException;
import com.grpc.SystemEvents;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/username/{username}")
    User GetUserByUsername(@PathVariable String username) {

        return userRepository.findByUsername(username);
    }

    @GetMapping("/users")
    List<User> GetAll(){
        LogActivity(new Date().toString(), "UserMicroservice", "Default", "GET", "User", true, 200);
        return userService.GetAll();

    }

    @PostMapping("/register")
    User Add(@RequestBody User newUser)  {

        try {
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "POST", "User", true, 201);
            return  userService.Add(newUser);
        } catch (NotFoundException e){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "POST", "User", false, 404);
            throw e;
        }
        catch (ConstraintViolationException e){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "POST", "User", false, 400);
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "POST", "User", false, 500);
            throw new InternalServerException();
        }

    }

    @GetMapping("/users/{id}")
    User GetById(@PathVariable Long id){
        try{
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "GET", "User", true, 200);
            return userService.GetById(id);
        }
        catch (NotFoundException ex){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "GET", "User", false, 404);
            throw ex;
        }
        catch (Exception ex){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "GET", "User", false, 500);
            throw new InternalServerException();
        }

    }

    @PutMapping("/users/{id}")
    User Update(@RequestBody User newUser, @PathVariable Long id){
        try {
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "PUT", "User", true, 200);
            return userService.Update(newUser, id);
        }
        catch (ConstraintViolationException ex) {
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "PUT", "User", false, 400);
            throw new BadRequestException(ex.getMessage());
        }
        catch (NotFoundException ex) {
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "PUT", "User", false, 404);
            throw ex;
        }
        catch (Exception ex) {
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "PUT", "User", false, 500);
            throw new InternalServerException();
        }
    }

    @PutMapping("/users/picture/{id}")
    void UpdatePicture(@RequestParam("picture") MultipartFile file , @PathVariable String id){
        try {
            userService.UpdatePicture(file, Long.valueOf(id));
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
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "DELETE", "User", true, 204);
            userService.Delete(id);
        }
        catch (EmptyResultDataAccessException e){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "DELETE", "User", false, 404);
            throw new NotFoundException("user",id);
            //throw new UserNotFoundException(id);
        }
        catch (Exception e){
            LogActivity(new Date().toString(), "UserMicroservice", "Default", "DELETE", "User", false, 500);
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

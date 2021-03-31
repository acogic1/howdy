package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubscriptionController {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    SubscriptionController(SubscriptionRepository subscriptionRepository,UserRepository userRepository){
        this.subscriptionRepository=subscriptionRepository;
        this.userRepository=userRepository;
    }

    @GetMapping("/subscriptions")
    List<Subscription> all(){
        try {
            return  subscriptionRepository.findAll();
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @PostMapping("/subscriptions")
    Subscription newSubscription(@RequestBody Subscription newSubscription)  {

        try {
            Optional<User> user_follower=userRepository.findById(newSubscription.getId_follower().getId());
            Optional<User> user_following=userRepository.findById(newSubscription.getId_following().getId());
            return  subscriptionRepository.save(newSubscription);
        } catch (NotFoundException e){
            throw e;
        }
        catch (ConstraintViolationException e){
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            throw new InternalServerException();
        }

    }

    @GetMapping("/subscriptions/{id}")
    Subscription one(@PathVariable Long id){
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("subscription",id));
    }

    @PutMapping("/subscriptions/{id}")
    Subscription replaceSubscription(@RequestBody Subscription newSubscription, @PathVariable Long id){
        try {
            return subscriptionRepository.findById(id)
                    .map(subscription -> {
                        try {
                            subscription.setId_follower(newSubscription.getId_follower());
                            subscription.setId_following(newSubscription.getId_following());
                            return subscriptionRepository.save(subscription);
                        }
                        catch (ConstraintViolationException e){
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() ->{
                        newSubscription.setId(id);
                        return subscriptionRepository.save(newSubscription);
                    });

        }
        catch (NotFoundException e){
            throw e;
        }
        catch (ConstraintViolationException e){
            throw new BadRequestException(e.getMessage());
        }
        catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping("/subscriptions/{id}")
    void deleteSubscription(@PathVariable Long id) {
        try {
            subscriptionRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("subscription",id);
            //throw new UserNotFoundException(id);
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }
}

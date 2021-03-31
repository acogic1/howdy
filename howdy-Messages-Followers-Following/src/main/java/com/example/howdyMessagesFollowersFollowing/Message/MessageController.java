package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.example.howdyMessagesFollowersFollowing.Subscription.SubscriptionRepository;
import com.example.howdyMessagesFollowersFollowing.User.User;
import com.example.howdyMessagesFollowersFollowing.User.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/messages")
    List<Message> all() {
        try {
            return messageRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException();
        }

    }

    @PostMapping("/messages")
    Message newMessage(@RequestBody Message newMessage) {

        try {
            Optional<User> user_sender = userRepository.findById(newMessage.getId_sender().getId());
            Optional<User> user_reciever = userRepository.findById(newMessage.getId_reciever().getId());
            return messageRepository.save(newMessage);
        } catch (NotFoundException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException();
        }

    }

    @GetMapping("/messages/{id}")
    Message one(@PathVariable Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("message", id));
    }

    @PutMapping("/messages/{id}")
    Message replaceMessage(@RequestBody Message newMessage, @PathVariable Long id) {
        try {
            return messageRepository.findById(id)
                    .map(message -> {
                        try {
                            message.setId_sender(newMessage.getId_sender());
                            message.setId_reciever(newMessage.getId_reciever());
                            message.setData_time(newMessage.getData_time());
                            message.setContent(newMessage.getContent());
                            return messageRepository.save(message);
                        } catch (ConstraintViolationException e) {
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() -> {
                        newMessage.setId(id);
                        return messageRepository.save(newMessage);
                    });

        } catch (NotFoundException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        try {
            messageRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("message", id);
            //throw new UserNotFoundException(id);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}

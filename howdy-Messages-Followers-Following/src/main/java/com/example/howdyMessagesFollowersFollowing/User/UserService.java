package com.example.howdyMessagesFollowersFollowing.User;

import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.BadRequestException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.InternalServerException;
import com.example.howdyMessagesFollowersFollowing.ExceptionClasses.NotFoundException;
import com.example.howdyMessagesFollowersFollowing.Message.MessageRepository;
import com.example.howdyMessagesFollowersFollowing.Message.MessageService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    MessageService messageService;
    //@Autowired
    //RestTemplate restTemplate;

    public List<User> GetAll(){
        List<User> users=userRepository.findAll();
        return users;
    }

    public User GetById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("user",id));
        return user;
    }

    public User Add(User newUser){
        try {
            return  userRepository.save(newUser);
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

    public User Update(User newUser,Long id){
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        try {
                            user.setUsername(newUser.getUsername());
                            return userRepository.save(user);
                        }
                        catch (ConstraintViolationException e){
                            throw new BadRequestException(e.getMessage());
                        }
                        catch (Exception e){
                            throw new BadRequestException(e.getMessage());
                        }
                    })
                    .orElseGet(() ->{
                        newUser.setId(id);
                        return userRepository.save(newUser);
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

    public void Delete(Long id){
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new NotFoundException("user",id);
        }
        catch (Exception e){
            throw new InternalServerException();
        }
    }
}

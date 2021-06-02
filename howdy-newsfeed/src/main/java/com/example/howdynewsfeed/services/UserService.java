package com.example.howdynewsfeed.services;

import com.example.howdynewsfeed.Exceptions.InternalServerException;
import com.example.howdynewsfeed.Exceptions.NotFoundException;
import com.example.howdynewsfeed.models.Post;
import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository UserRepository;

    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public List<User> getUsers() {
        try {
            List<User> users =UserRepository.findAll();
            return new ArrayList<>(UserRepository.findAll());
        }
        catch (Exception e){
            throw new InternalServerException();
        }    }

    public User getUserById(Long Id) {
        return UserRepository.findById(Id)
                .orElseThrow(() -> new NotFoundException("user",Id));
    }

    public Long findIdByUsername(String username) {
        List<User> allUsers = UserRepository.findAll();
        Long id = 0L;
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                id=user.getId();
            }
        }
        if(id.equals(0L)) throw new NotFoundException("id by username",username);
        return id;
    }

}

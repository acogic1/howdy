package com.example.howdynewsfeed.repository;

import com.example.howdynewsfeed.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    //public List<User> findAll(Long id);
}

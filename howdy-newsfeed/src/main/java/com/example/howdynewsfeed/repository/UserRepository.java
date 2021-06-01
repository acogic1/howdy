package com.example.howdynewsfeed.repository;

import com.example.howdynewsfeed.models.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    //public List<User> findAll(Long id);
    List<User> findAll();
    User findById(ID id);
    User findByUsername(String username);

}

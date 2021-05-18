package com.example.howdyuser.User;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User,Long> {
    public List<User> findAll();
    User findById(ID id);
    void deleteById(ID id);
    User findByUsername(String username);
}

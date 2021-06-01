package com.example.howdyMessagesFollowersFollowing.User;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    public List<User> findAll();
    User findById(ID id);
    void deleteById(ID id);
    User findByUsername(String username);
}

package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.User.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
    public List<Subscription> findAll();
    Subscription findById(ID id);
    void deleteById(ID id);
}

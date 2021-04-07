package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.User.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
    public List<Subscription> findAll();
    Subscription findById(ID id);
    void deleteById(ID id);

    @Query(value = "SELECT DISTINCT id_follower " +
            "FROM subscription " +
            "WHERE subscription.id_following=:user_id",
            nativeQuery = true)
    List<Tuple> findFollowersByUser(@Param("user_id")Long userid);

    @Query(value = "SELECT DISTINCT id_following " +
            "FROM subscription " +
            "WHERE subscription.id_follower=:user_id",
            nativeQuery = true)
    List<Tuple> findFollowingByUser(@Param("user_id")Long userid);
}

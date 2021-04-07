package com.example.howdyMessagesFollowersFollowing.Message;

import com.example.howdyMessagesFollowersFollowing.Subscription.Subscription;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    public List<Message> findAll();
    Message findById(ID id);
    void deleteById(ID id);

    @Query(value = "SELECT DISTINCT id_sender, id_reciever " +
            "FROM message " +
            "WHERE message.id_sender=:user_id OR message.id_reciever=:user_id",
            nativeQuery = true)
    List<Tuple> findInboxByUser(@Param("user_id")Long userid);

    @Query(value = "SELECT id, id_sender, id_reciever, date_time, content " +
            "FROM message " +
            "WHERE (message.id_sender=:user1 AND message.id_reciever=:user2) OR (message.id_sender=:user2 AND message.id_reciever=:user1)",
            nativeQuery = true)
    List<Tuple> findConversationByUsers(@Param("user1")Long user1id,@Param("user2")Long user2id);
}

package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.User.User;

import javax.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_follower")
    private User id_follower;

    @ManyToOne
    @JoinColumn(name = "id_following")
    private User id_following;

    public Subscription(Long id, User id_follower, User id_following) {
        this.id = id;
        this.id_follower = id_follower;
        this.id_following = id_following;
    }

    public Subscription(User id_follower, User id_following) {
        this.id_follower = id_follower;
        this.id_following = id_following;
    }

    public Subscription(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getId_follower() {
        return id_follower;
    }

    public void setId_follower(User id_follower) {
        this.id_follower = id_follower;
    }

    public User getId_following() {
        return id_following;
    }

    public void setId_following(User id_following) {
        this.id_following = id_following;
    }
}

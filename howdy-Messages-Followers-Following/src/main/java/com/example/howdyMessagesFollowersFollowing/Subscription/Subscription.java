package com.example.howdyMessagesFollowersFollowing.Subscription;

import com.example.howdyMessagesFollowersFollowing.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "Variable id_follower must not be null")
    @JoinColumn(name = "id_follower")
    private User id_follower;

    @ManyToOne
    @NotNull(message = "Variable id_following must not be null")
    @JoinColumn(name = "id_following")
    private User id_following;

    public Subscription(Long id,@NotNull(message = "Variable id_follower must not be null") User id_follower,@NotNull(message = "Variable id_following must not be null") User id_following) {
        this.id = id;
        this.id_follower = id_follower;
        this.id_following = id_following;
    }

    public Subscription(@NotNull(message = "Variable id_follower must not be null") User id_follower,@NotNull(message = "Variable id_following must not be null") User id_following) {
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

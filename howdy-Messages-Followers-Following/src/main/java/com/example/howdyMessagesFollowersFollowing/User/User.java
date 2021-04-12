package com.example.howdyMessagesFollowersFollowing.User;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.swing.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Variable username must not be null")
    private String username;
    @Lob
    private Byte[] picture;

    public User(Long id,@NotNull(message = "Variable username must not be null") String username) {
        this.id = id;
        this.username = username;
    }
    public User(@NotNull(message = "Variable username must not be null") String username) {
        this.username = username;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }
}

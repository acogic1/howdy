package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    private String password;


    public User () {

    }

    public User (Long id, String username) {
        this.id=id;
        this.username=username;
    }

    /*public User (Long id, String username, String password) {
        this.id=id;
        this.username=username;
        this.password=password;
    }*/

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

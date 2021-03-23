package com.example.howdyuser.User;



import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.awt.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Variable email must not be null")
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    private String email;
    @NotNull(message = "Variable username must not be null")
    private String username;
    @NotNull(message = "Variable password must not be null")
    private String password;
    @NotNull(message = "Variable description must not be null")
    private String description;
    private ImageIcon picture;

    public User(Long id,@NotNull(message = "Variable email must not be null") @Email(regexp=".*@.*\\..*", message = "Email should be valid") String email,@NotNull(message = "Variable username must not be null") String username,@NotNull(message = "Variable password must not be null") String password,@NotNull(message = "Variable description must not be null") String description) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public User(@NotNull(message = "Variable email must not be null") @Email(regexp=".*@.*\\..*", message = "Email should be valid") String email,@NotNull(message = "Variable username must not be null") String username,@NotNull(message = "Variable password must not be null") String password,@NotNull(message = "Variable description must not be null") String description) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + picture +
                '}';
    }
}

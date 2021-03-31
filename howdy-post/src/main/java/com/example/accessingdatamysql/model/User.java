package com.example.accessingdatamysql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Reaction> reactions;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
}

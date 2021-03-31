package com.example.accessingdatamysql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Reaction> reactions;

    @ManyToMany
    Set<Tag> tags;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }

}
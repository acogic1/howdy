package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull
    private User userId;

    @NotNull
    @Column (name = "content")
    private String content;

    public Post() {}

    public Post(Long id, User userId, String content) {
        this.id=id;
        this.userId=userId;
        this.content=content;
    }

    public Post( User userId, String content) {
        this.userId=userId;
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}

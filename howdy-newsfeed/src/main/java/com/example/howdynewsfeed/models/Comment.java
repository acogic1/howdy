package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull
    private User userId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @NotNull
    private Post postId;

    @Column(name = "content")
    @NotNull
    private String content;

    public Comment() {}

    public Comment(Long id, User userId, Post postId, String content) {
        this.id=id;
        this.userId=userId;
        this.postId=postId;
        this.content=content;
    }

    public Comment(User userId, Post postId, String content) {
        this.userId=userId;
        this.postId=postId;
        this.content=content;
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

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

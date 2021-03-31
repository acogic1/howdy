package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @NotNull
    private Long userId;

    @JoinColumn(name = "post_id")
    @NotNull
    private Long postId;

    @Column(name = "content")
    @NotNull
    private String content;

    public Comment() {}

    public Comment(Long id, Long userId, Long postId, String content) {
        this.id=id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

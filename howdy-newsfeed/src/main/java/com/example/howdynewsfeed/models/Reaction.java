package com.example.howdynewsfeed.models;

import javax.persistence.*;

@Entity
public class Reaction {
    enum Type {Like, Unlike, Sad, FakeNews, Angry, Funny, Racism, HateSpeech, Sarcasm, Motivational};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    @JoinColumn(name = "post_id")
    private Long postId;
    private Enum type;

    public Reaction() {}

    public Reaction(Long id, Long userId, Long postId, Enum type) {

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

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }
}

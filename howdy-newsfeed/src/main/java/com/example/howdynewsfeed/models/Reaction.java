package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Reaction {
    public enum TypeReaction {Like, Unlike, Sad, FakeNews, Angry, Funny, Racism, HateSpeech, Sarcasm, Motivational};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @NotNull
    private Long userId;

    @JoinColumn(name = "post_id")
    @NotNull
    private Long postId;

    @Column(name = "type_reaction")
    @NotNull
    private TypeReaction typeReaction;

    public Reaction() {}

    public Reaction(Long id, Long userId, Long postId, TypeReaction type) {
        this.id=id;
        this.userId=userId;
        this.postId=postId;
        this.typeReaction=type;
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

    public TypeReaction getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(TypeReaction type) {
        this.typeReaction = type;
    }
}

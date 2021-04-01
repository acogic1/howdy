package com.example.howdynewsfeed.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Reaction {
    public enum TypeReaction {Like, Dislike, Sad, FakeNews, Angry, Funny, Racism, HateSpeech, Sarcasm, Motivational};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull
    private User userId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @NotNull
    private Post postId;

    @Column(name = "type_reaction")
    @NotNull
    private TypeReaction typeReaction;

    public Reaction() {}

    public Reaction(Long id, User userId, Post postId, TypeReaction type) {
        this.id=id;
        this.userId=userId;
        this.postId=postId;
        this.typeReaction=type;
    }
    public Reaction(User userId, Post postId, TypeReaction type) {
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

    public TypeReaction getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(TypeReaction type) {
        this.typeReaction = type;
    }
}

package com.example.accessingdatamysql.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;


    public Reaction(User user, Post post, ReactionType reactionType) {
        this.user = user;
        this.post = post;
        this.reactionType = reactionType;
        this.date = new Date();
    }
    public enum ReactionType {
        LIKE,
        DISLIKE,
        HAHA,
        SAD
    }

}

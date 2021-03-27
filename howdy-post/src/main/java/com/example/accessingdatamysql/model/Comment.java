package com.example.accessingdatamysql.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long userid;

    private String text;

    @ManyToOne
    private Post post;


    public Comment(Long userid, String text, Post post) {
        this.userid = userid;
        this.text = text;
        this.post = post;
    }
}

package com.example.accessingdatamysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long userId;

    private String text;

    private Date date;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();


    public Post(String text, Long userId) {
        this.text = text;
        this.userId = userId;
        this.date = new Date();
    }


}
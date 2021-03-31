package com.example.accessingdatamysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@Data
public class Status {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    private String text;


    public Status(String text, Integer userId) {
        this.text = text;
        this.userId = userId;

    }
}
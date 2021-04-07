package com.example.accessingdatamysql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@NoArgsConstructor
public class Post  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private Set<Reaction> reactions;

    @JsonIgnore
    @ManyToMany
    Set<Tag> tags;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", comments=" + comments +
                ", reactions=" + reactions +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(user, post.user) && Objects.equals(content, post.content) && Objects.equals(date, post.date) && Objects.equals(comments, post.comments) && Objects.equals(reactions, post.reactions) && Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, content, date, comments, reactions, tags);
    }

}
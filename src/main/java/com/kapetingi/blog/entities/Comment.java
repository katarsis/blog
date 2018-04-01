package com.kapetingi.blog.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private String text;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User creator;

    public Comment(String text, Post post, User creator) {
        this.text = text;
        this.post = post;
        this.creator = creator;
    }
}
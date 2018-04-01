package com.kapetingi.blog.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Data
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String body;
    private LocalDateTime dateCreated;

    @ManyToOne
    private User creator;


}

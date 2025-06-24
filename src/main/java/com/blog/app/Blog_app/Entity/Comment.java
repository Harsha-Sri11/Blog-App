package com.blog.app.Blog_app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_Id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}

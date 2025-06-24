package com.blog.app.Blog_app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(length = 100, nullable = false)
    private String postTitle;
    @Column(length = 1000, nullable = false)
    private String content;
    private String imageName;
    private Date postedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;  // Many posts can be under a single category
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;          //Many posts can be made a by a single user
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}

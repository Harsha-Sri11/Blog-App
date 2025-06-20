package com.blog.app.Blog_app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name="user_name",nullable = false)
    private String name;

    private String password;

    private String email;

    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();  // User can have many posts
}

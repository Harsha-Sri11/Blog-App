package com.blog.app.Blog_app.payloads;

import com.blog.app.Blog_app.Entity.Category;
import com.blog.app.Blog_app.Entity.Comment;
import com.blog.app.Blog_app.Entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PostDTO {
    private String postId;
    private String postTitle;
    private String content;
    private Date postedDate;
    private String imageName;
    private CategoryDTO category;  // Many posts can be under a single category
    private UserDTO user;
    private Set<Comment> comments = new HashSet<>();
}

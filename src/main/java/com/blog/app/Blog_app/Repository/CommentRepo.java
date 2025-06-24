package com.blog.app.Blog_app.Repository;

import com.blog.app.Blog_app.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}

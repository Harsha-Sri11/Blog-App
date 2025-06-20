package com.blog.app.Blog_app.Repository;

import com.blog.app.Blog_app.Entity.Category;
import com.blog.app.Blog_app.Entity.Post;
import com.blog.app.Blog_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findPostListByUser(User user);
    List<Post> findPostListByCategory(Category category);
}

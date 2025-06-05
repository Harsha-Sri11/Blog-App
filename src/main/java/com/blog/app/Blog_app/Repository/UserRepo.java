package com.blog.app.Blog_app.Repository;

import com.blog.app.Blog_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}

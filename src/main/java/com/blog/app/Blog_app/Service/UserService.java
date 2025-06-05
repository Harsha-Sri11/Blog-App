package com.blog.app.Blog_app.Service;

import com.blog.app.Blog_app.Entity.User;
import com.blog.app.Blog_app.Exceptions.ResourceNotFoundException;
import com.blog.app.Blog_app.Repository.UserRepo;
import com.blog.app.Blog_app.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        this.userToDto(savedUser);
        return userDTO;


    }

    UserDTO updateUser(UserDTO userDTO,int userId){
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        User updatedUser = this.userRepo.save(user);
        this.userToDto(updatedUser);

        return userDTO;
    }

    UserDTO getUserById(int userId){
        return null;
    }

    List<UserDTO> getAllUsers(){
        return null;
    }

    String deleteUser(int userId){
        return null;
    }

    public UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId()); // Correctly setting the ID
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        return userDTO; // Return the mapped object instead of null
    }

    public User dtoToUser(UserDTO userDTO){
       User user = new User();
        user.setId(userDTO.getId()); // Correctly setting the ID
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}




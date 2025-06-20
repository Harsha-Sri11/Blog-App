package com.blog.app.Blog_app.Service;

import com.blog.app.Blog_app.Entity.User;
import com.blog.app.Blog_app.Exceptions.ResourceNotFoundException;
import com.blog.app.Blog_app.Repository.UserRepo;
import com.blog.app.Blog_app.payloads.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO); //converting UserDTO to User
        User savedUser = userRepo.save(user);
        UserDTO savedUserDTO= userToDto(savedUser); //converting user back to UserDTO
        return savedUserDTO;
    }

    public UserDTO updateUser(UserDTO userDTO,int userId){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
        User updatedUser = userRepo.save(user);
        UserDTO updateuserDTO= userToDto(updatedUser);
        return updateuserDTO;
    }

    public UserDTO getUser(int userId){
        User getUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        return userToDto(getUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> getUsers = userRepo.findAll();
        return getUsers.stream().map(this::userToDto).collect(Collectors.toList()); // Return the DTO list
    }

    public void deleteUser(int userId){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        userRepo.delete(user);
    }

    public UserDTO userToDto(User user) {
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
//        userDTO.setId(user.getId()); // Correctly setting the ID
//        userDTO.setName(user.getName());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setAbout(user.getAbout());
        return userDTO; // Return the mapped object instead of null
    }

    public User dtoToUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO,User.class);
//       User user = new User();
//        user.setId(userDTO.getId()); // Correctly setting the ID
//        user.setName(userDTO.getName());
//        user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//        user.setAbout(userDTO.getAbout());
        return user;
    }
}




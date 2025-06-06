package com.blog.app.Blog_app.Controllers;


import com.blog.app.Blog_app.Service.UserService;
import com.blog.app.Blog_app.payloads.ApiResponse;
import com.blog.app.Blog_app.payloads.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);

    }

    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable int userId){
        return ResponseEntity.ok(userService.updateUser(userDTO,userId));
    }

    @GetMapping("/getuser/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<ApiResponse> deleteuser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
}


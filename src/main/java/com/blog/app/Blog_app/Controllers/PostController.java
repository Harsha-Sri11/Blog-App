package com.blog.app.Blog_app.Controllers;

import com.blog.app.Blog_app.Entity.Post;
import com.blog.app.Blog_app.Service.PostService;
import com.blog.app.Blog_app.payloads.ApiResponse;
import com.blog.app.Blog_app.payloads.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/createPost")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable int userId, @PathVariable int categoryId){
        PostDTO createPost = postService.createPost(postDTO,userId,categoryId);
    return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable int categoryId){
        List<PostDTO> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable int userId){
    List<PostDTO> getPosts = postService.getPostsByUser(userId);
    return new ResponseEntity<>(getPosts,HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> getAllPosts = postService.getAllPosts();
        return new ResponseEntity<>(getAllPosts,HttpStatus.OK);
    }

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int postId){
        PostDTO getPost = postService.getPostById(postId);
        return new ResponseEntity<>(getPost,HttpStatus.OK);

    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable int postId){
        PostDTO updatedPost = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }
}

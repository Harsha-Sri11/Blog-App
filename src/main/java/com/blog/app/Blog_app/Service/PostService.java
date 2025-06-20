package com.blog.app.Blog_app.Service;

import com.blog.app.Blog_app.Entity.Category;
import com.blog.app.Blog_app.Entity.Post;
import com.blog.app.Blog_app.Entity.User;
import com.blog.app.Blog_app.Exceptions.ResourceNotFoundException;
import com.blog.app.Blog_app.Repository.CategoryRepo;
import com.blog.app.Blog_app.Repository.PostRepo;
import com.blog.app.Blog_app.Repository.UserRepo;
import com.blog.app.Blog_app.payloads.PostDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId){
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setPostedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post savePost = postRepo.save(post);
    return modelMapper.map(savePost, PostDTO.class);
    }

    public List<PostDTO> getPostsByCategory(int categoryId){
        Category categoryIDs = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> postsbyCategory =postRepo.findPostListByCategory(categoryIDs);
        List<PostDTO> postDTOListByCategory = postsbyCategory.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOListByCategory;
    }


    public List<PostDTO> getPostsByUser(int userId){
        User userIDs = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        List<Post> postsByUser = postRepo.findPostListByUser(userIDs);
        List<PostDTO> postsListByUser = postsByUser.stream().map(userpost -> modelMapper.map(userpost, PostDTO.class)).collect(Collectors.toList());
        return postsListByUser;

    }

    public List<PostDTO> getAllPosts(){
        List<Post> getPosts = postRepo.findAll();
        List<PostDTO> getPostDtos = getPosts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return getPostDtos;
    }


    public PostDTO getPostById(int postId){
        Post getPost = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId",postId));
        PostDTO getPostDTO = modelMapper.map(getPost, PostDTO.class);
        return getPostDTO;

    }


    public PostDTO updatePost(PostDTO postDTO,int postId){
        Post updatePost = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        updatePost.setPostTitle(postDTO.getPostTitle());
        updatePost.setContent(postDTO.getContent());
        updatePost.setPostedDate(new Date());
        Post updatedPost = postRepo.save(updatePost);
        PostDTO updatedPostDto = modelMapper.map(updatedPost, PostDTO.class);
        return updatedPostDto;
    }

    public void deletePost(int postId){
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
            postRepo.delete(post);
    }

//
//    List<Post> searchPosts(String keyword){
//
//    }
}

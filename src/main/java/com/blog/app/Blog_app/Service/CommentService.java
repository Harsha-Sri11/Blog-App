package com.blog.app.Blog_app.Service;

import com.blog.app.Blog_app.Entity.Comment;
import com.blog.app.Blog_app.Entity.Post;
import com.blog.app.Blog_app.Exceptions.ResourceNotFoundException;
import com.blog.app.Blog_app.Repository.CommentRepo;
import com.blog.app.Blog_app.Repository.PostRepo;
import com.blog.app.Blog_app.payloads.CommentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CommentDTO createComment(CommentDTO commentDTO, int postId){
    Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","postId",postId));
    Comment comment = modelMapper.map(commentDTO, Comment.class);
    comment.setPost(post);
    Comment savedComment = commentRepo.save(comment);
    return modelMapper.map(savedComment, CommentDTO.class);

    }

    public CommentDTO getCommentbyId(int commentId){
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","commentId",+commentId));
        return modelMapper.map(comment, CommentDTO.class);
    }

    public void deleteComment(int commentId){
    Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","commentId",commentId));
        commentRepo.delete(comment);
    }
}

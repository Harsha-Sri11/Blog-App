package com.blog.app.Blog_app.Controllers;

import com.blog.app.Blog_app.Service.CommentService;
import com.blog.app.Blog_app.payloads.ApiResponse;
import com.blog.app.Blog_app.payloads.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor

public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable int postId){
        CommentDTO createComment = commentService.createComment(commentDTO,postId);
        return new ResponseEntity<>(createComment,HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> commentDTO(@PathVariable int commentId){
        CommentDTO getComment = commentService.getCommentbyId(commentId);
        return new ResponseEntity<>(getComment,HttpStatus.OK);

    }
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
    }


}

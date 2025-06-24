package com.blog.app.Blog_app.payloads;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {

    private int comment_Id;
    private String content;

}

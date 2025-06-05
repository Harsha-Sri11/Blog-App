package com.blog.app.Blog_app.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private int id;

    private String name;

    private String password;

    private String email;
}


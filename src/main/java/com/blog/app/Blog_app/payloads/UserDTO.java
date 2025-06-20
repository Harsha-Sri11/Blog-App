package com.blog.app.Blog_app.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private int userId;

    @NotBlank
    @Size(min=4, message = "Username must be of min 4 characters")
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Email(message = "Email address is not valid")
    private String email;


    private String about;
}


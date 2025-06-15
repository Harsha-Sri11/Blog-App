package com.blog.app.Blog_app.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private int categoryId;

    @NotBlank
    @Size(message = "Must atleast be a word")
    private String categoryTitle;

    @NotBlank
    private String categoryDescription;
}

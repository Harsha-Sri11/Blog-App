package com.blog.app.Blog_app.Controllers;


import com.blog.app.Blog_app.Service.CategoryService;
import com.blog.app.Blog_app.payloads.ApiResponse;
import com.blog.app.Blog_app.payloads.CategoryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
       return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO ,@PathVariable int categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO,categoryId));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/getCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> getcategory(@PathVariable int categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId),HttpStatus.OK);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getAllcategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }


}

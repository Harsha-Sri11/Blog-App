package com.blog.app.Blog_app.Service;

import com.blog.app.Blog_app.Entity.Category;
import com.blog.app.Blog_app.Exceptions.ResourceNotFoundException;
import com.blog.app.Blog_app.Repository.CategoryRepo;
import com.blog.app.Blog_app.payloads.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

    public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category =  this.modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return this.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId){
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedcategory = categoryRepo.save(category);
        return this.modelMapper.map(updatedcategory, CategoryDTO.class);
    }

    public void deleteCategory(int categoryId){
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
    }

    public CategoryDTO getCategory(int categoryId){
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
       return this.modelMapper.map(category, CategoryDTO.class);
    }

    public List<CategoryDTO> getCategories(){
        List<Category> categoryList = categoryRepo.findAll();
        System.out.println(categoryList);
        return categoryList.stream().map((category) -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }
}

package com.demo.videolibrary.category.controller;

import com.demo.videolibrary.category.controller.api.CategoryApi;
import com.demo.videolibrary.category.dto.CategoryDto;
import com.demo.videolibrary.category.request.CreateCategoryRequest;
import com.demo.videolibrary.category.request.UpdateCategoryRequest;
import com.demo.videolibrary.category.service.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController implements CategoryApi {

    private final CategoryServiceImpl categoryServiceImpl;

    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategory(String keyword) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categoryServiceImpl.findAll(keyword));
    }

    @Override
    public ResponseEntity<CategoryDto> getCategory(String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categoryServiceImpl.findById(id));
    }

    @Override
    public ResponseEntity<CategoryDto> createCategory(CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryServiceImpl.add(request));
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(UpdateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryServiceImpl.update(request));
    }

    @Override
    public void deleteCategory(String id) {
        categoryServiceImpl.deleteById(id);
    }
}

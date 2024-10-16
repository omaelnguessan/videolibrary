package com.demo.videolibrary.category.service;

import com.demo.videolibrary.category.dto.CategoryDto;
import com.demo.videolibrary.category.request.CreateCategoryRequest;
import com.demo.videolibrary.category.request.UpdateCategoryRequest;
import com.demo.videolibrary.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(String keyword);
    CategoryDto findById(String id);
    CategoryDto add(CreateCategoryRequest createCategoryRequest);
    CategoryDto update(UpdateCategoryRequest updateCategoryRequest);
    void deleteById(String id);
}

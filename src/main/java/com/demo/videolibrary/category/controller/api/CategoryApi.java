package com.demo.videolibrary.category.controller.api;

import com.demo.videolibrary.category.dto.CategoryDto;
import com.demo.videolibrary.category.request.CreateCategoryRequest;
import com.demo.videolibrary.category.request.UpdateCategoryRequest;
import com.demo.videolibrary.exception.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.videolibrary.utils.Constants.APP_ROOT_V1;

public interface CategoryApi {

    @GetMapping(value = APP_ROOT_V1 + "/category/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategory(@PathVariable("search") String search);

    @GetMapping(value = APP_ROOT_V1 + "/category/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> getCategory(@PathVariable("id") String id) throws ResourceNotFoundException;

    @PostMapping(value = APP_ROOT_V1 + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest request);

    @PutMapping(value = APP_ROOT_V1 + "/category/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> updateCategory(@RequestBody UpdateCategoryRequest request) throws ResourceNotFoundException;

    @DeleteMapping(value = APP_ROOT_V1 + "/category/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCategory(@PathVariable("id") String id) throws ResourceNotFoundException;
}

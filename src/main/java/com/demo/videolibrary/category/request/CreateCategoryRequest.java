package com.demo.videolibrary.category.request;

public record CreateCategoryRequest(
        String name,
        String description
) {
}

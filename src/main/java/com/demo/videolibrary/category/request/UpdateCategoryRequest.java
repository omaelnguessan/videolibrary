package com.demo.videolibrary.category.request;

public record UpdateCategoryRequest(
        String id,
        String name,
        String description
) {
}

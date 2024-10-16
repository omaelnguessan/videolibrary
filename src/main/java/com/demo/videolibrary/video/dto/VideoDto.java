package com.demo.videolibrary.video.dto;

import com.demo.videolibrary.category.Category;
import com.demo.videolibrary.utils.TypeVideo;

import java.util.UUID;

public record VideoDto(
        UUID id,
        String title,
        String url,
        String description,
        String cover,
        TypeVideo videoType,
        Category category
) {
}

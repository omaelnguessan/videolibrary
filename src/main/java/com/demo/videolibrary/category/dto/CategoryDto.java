package com.demo.videolibrary.category.dto;


import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        String description
) {
}

package com.demo.videolibrary.category.dto;

import com.demo.videolibrary.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDtoMapper {
    CategoryDtoMapper INSTANCE = Mappers.getMapper(CategoryDtoMapper.class);

    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);
}

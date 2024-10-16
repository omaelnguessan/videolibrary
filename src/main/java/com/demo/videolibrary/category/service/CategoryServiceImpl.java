package com.demo.videolibrary.category.service;

import com.demo.videolibrary.category.Category;
import com.demo.videolibrary.category.CategoryRepository;
import com.demo.videolibrary.category.dao.CategoryDao;
import com.demo.videolibrary.category.dto.CategoryDto;
import com.demo.videolibrary.category.dto.CategoryDtoMapper;
import com.demo.videolibrary.category.request.CreateCategoryRequest;
import com.demo.videolibrary.category.request.UpdateCategoryRequest;
import com.demo.videolibrary.exception.DuplicateResourceException;
import com.demo.videolibrary.exception.RequestValidationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public List<CategoryDto> findAll(String keyword) {
        if(keyword.isEmpty()|| keyword == null) {
            return categoryRepository.findAll()
                    .stream()
                    .map(categoryDtoMapper::toCategoryDto)
                    .collect(Collectors.toList());
        }
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(String id) {
        return categoryRepository.findById(UUID.fromString(id))
                .map(categoryDtoMapper::toCategoryDto)
                .orElseThrow(() -> {
                    log.error("Category with id [%s] not found".formatted(id));
            return new EntityNotFoundException(
                    "Category with id [%s] not found".formatted(id));
        });
    }

    @Override
    public CategoryDto add(CreateCategoryRequest createCategoryRequest) {
        var name = createCategoryRequest.name();
        if(categoryDao.existsCategoryByName(name)) {
            log.error("Category with name [%s] already exists".formatted(name));
            throw new DuplicateResourceException(
                    "Category with name [%s] already exists".formatted(name)
            );
        }

        Category category = new Category();
        category.setName(createCategoryRequest.name());
        category.setDescription(createCategoryRequest.description());
        Category categorySaved = categoryRepository.save(category);

        return categoryDtoMapper.toCategoryDto(categorySaved);
    }

    @Override
    public CategoryDto update(UpdateCategoryRequest updateCategoryRequest) {
        var id = updateCategoryRequest.id();
        Category category = categoryRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Category with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Category with id [%s] not found".formatted(id));
                });

        boolean changes = false;

        if (updateCategoryRequest.name() != null && !updateCategoryRequest.name().equals(category.getName())) {
            if (categoryDao.existsCategoryByName(updateCategoryRequest.name())) {
                log.error("Category with name [%s] already exists".formatted(updateCategoryRequest.name()));
                throw new DuplicateResourceException(
                        "name already taken"
                );
            }
            category.setName(updateCategoryRequest.name());
            changes = true;
        }

        if (updateCategoryRequest.description() != null && !updateCategoryRequest.description().equals(category.getDescription())) {
            category.setDescription(updateCategoryRequest.description());
            changes = true;
        }

        if (!changes) {
            log.error("Category with id [%s] not found".formatted(id));
            throw new RequestValidationException("no data changes found");
        }

        Category categorySaved = categoryRepository.save(category);

        return categoryDtoMapper.toCategoryDto(categorySaved);
    }

    @Override
    public void deleteById(String id)  {
        UUID uuid = UUID.fromString(id);
        Category category = categoryRepository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("Category with id [%s] not found".formatted(id));
                   return new EntityNotFoundException(
                            "Category with id [%s] not found".formatted(id));
                });

        categoryRepository.delete(category);
    }
}

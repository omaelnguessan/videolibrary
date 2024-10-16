package com.demo.videolibrary.category.dao;

import com.demo.videolibrary.category.Category;

import java.util.List;

public interface CategoryDao {
    boolean existsCategoryByName(String name);
    List<Category> search(String keyword);
}

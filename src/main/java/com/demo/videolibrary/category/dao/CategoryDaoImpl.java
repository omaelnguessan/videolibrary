package com.demo.videolibrary.category.dao;

import com.demo.videolibrary.category.Category;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsCategoryByName(String name) {
        String sql = "SELECT COUNT(*) FROM categories WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count != null && count > 0;
    }

    @Override
    public List<Category> search(String keyword) {
        String sql = "SELECT * FROM category WHERE name LIKE ? OR description LIKE ?";
        String keywordPattern = "%" + keyword + "%";
        return jdbcTemplate.query(sql, new Object[]{keywordPattern, keywordPattern}, new CategoryRowMapper());
    }
}

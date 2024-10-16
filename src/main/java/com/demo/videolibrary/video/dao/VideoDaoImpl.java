package com.demo.videolibrary.video.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class VideoDaoImpl implements VideoDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsVideoByTitle(String title) {
        String sql = "SELECT COUNT(*) FROM video WHERE title= ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, title);
        return count != null && count > 0;
    }
}

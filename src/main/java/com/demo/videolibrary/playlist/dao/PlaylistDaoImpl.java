package com.demo.videolibrary.playlist.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class PlaylistDaoImpl implements PlaylistDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean existsPlaylistByName(String name) {
        String sql = "SELECT COUNT(*) FROM playlist WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count != null && count > 0;
    }
}

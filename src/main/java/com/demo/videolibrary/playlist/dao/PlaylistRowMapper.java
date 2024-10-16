package com.demo.videolibrary.playlist.dao;


import com.demo.videolibrary.playlist.Playlist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlaylistRowMapper implements RowMapper<Playlist> {

    @Override
    public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Playlist playlist = new Playlist();
        playlist.setId(UUID.fromString(rs.getString("id")));
        playlist.setName(rs.getString("name"));
        playlist.setDescription(rs.getString("description"));
        playlist.setCover(rs.getString("cover"));
        return playlist;
    }
}

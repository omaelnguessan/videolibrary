package com.demo.videolibrary.video.dao;

import com.demo.videolibrary.video.Video;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VideoRowMapper implements RowMapper<Video> {
    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
        Video video = new Video();
        video.setId(UUID.fromString(rs.getString("id")));
        video.setTitle(rs.getString("title"));
        video.setCover(rs.getString("cover"));
        video.setDescription(rs.getString("description"));
        return video;
    }
}

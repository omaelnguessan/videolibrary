package com.demo.videolibrary.playlistvideo.service;

import com.demo.videolibrary.playlistvideo.PlaylistVideoRepository;
import com.demo.videolibrary.video.dto.VideoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PlaylistVideoServiceImpl implements PlaylistVideoService {

    private final PlaylistVideoRepository playlistVideoRepository;

    @Override
    public boolean addVideoToPlaylist(String videoId, String playlistId) {
        return false;
    }

    @Override
    public boolean removeVideoFromPlaylist(String videoId, String playlistId) {
        return false;
    }

    @Override
    public List<VideoDto> getVideoListByPlaylist(String playlistId) {
        return List.of();
    }
}

package com.demo.videolibrary.playlistvideo.service;

import com.demo.videolibrary.video.dto.VideoDto;

import java.util.List;

public interface PlaylistVideoService {
    boolean addVideoToPlaylist(String videoId, String playlistId);
    boolean removeVideoFromPlaylist(String videoId, String playlistId);
    List<VideoDto> getVideoListByPlaylist(String playlistId);
}

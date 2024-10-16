package com.demo.videolibrary.video.service;

import com.demo.videolibrary.video.dto.VideoDto;
import com.demo.videolibrary.video.request.CreateVideoRequest;
import com.demo.videolibrary.video.request.UpdateVideoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    List<VideoDto> getVideoList();
    VideoDto getVideoById(String id);
    VideoDto addVideo(CreateVideoRequest request, MultipartFile file) throws IOException;
    VideoDto updateVideo(UpdateVideoRequest request, MultipartFile file) throws IOException;
    void deleteVideo(String id);
}

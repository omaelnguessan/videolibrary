package com.demo.videolibrary.video.controller;

import com.demo.videolibrary.video.controller.api.VideoApi;
import com.demo.videolibrary.video.dto.VideoDto;
import com.demo.videolibrary.video.request.CreateVideoRequest;
import com.demo.videolibrary.video.request.UpdateVideoRequest;
import com.demo.videolibrary.video.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class VideoController implements VideoApi {

    private final VideoService videoService;

    @Override
    public ResponseEntity<List<VideoDto>> getVideos() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(videoService.getVideoList());
    }

    @Override
    public ResponseEntity<VideoDto> getVideo(String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(videoService.getVideoById(id));
    }

    @Override
    public ResponseEntity<VideoDto> createVideo(CreateVideoRequest request,
                                                MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(videoService.addVideo(request, file));
    }

    @Override
    public ResponseEntity<VideoDto> updateVideo(UpdateVideoRequest request,
                                                MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(videoService.updateVideo(request, file));
    }

    @Override
    public void deleteVideo(String id) {
        videoService.deleteVideo(id);
    }
}

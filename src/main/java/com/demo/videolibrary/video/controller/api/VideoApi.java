package com.demo.videolibrary.video.controller.api;

import com.demo.videolibrary.video.dto.VideoDto;
import com.demo.videolibrary.video.request.CreateVideoRequest;
import com.demo.videolibrary.video.request.UpdateVideoRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.demo.videolibrary.utils.Constants.APP_ROOT_V1;

public interface VideoApi {
    @GetMapping(value = APP_ROOT_V1 + "/video/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VideoDto>> getVideos();

    @GetMapping(value = APP_ROOT_V1 + "/video/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VideoDto> getVideo(@PathVariable("id") String id);

    @PostMapping(value = APP_ROOT_V1 + "/video/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VideoDto> createVideo(@ModelAttribute CreateVideoRequest request, @RequestParam("image") MultipartFile file) throws IOException;

    @PutMapping(value = APP_ROOT_V1 + "/video/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VideoDto> updateVideo(@ModelAttribute UpdateVideoRequest request, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException;

    @DeleteMapping(value = APP_ROOT_V1 + "/video/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteVideo(@PathVariable("id") String id);
}

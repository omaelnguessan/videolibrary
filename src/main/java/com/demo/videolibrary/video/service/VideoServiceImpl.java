package com.demo.videolibrary.video.service;

import com.demo.videolibrary.category.Category;
import com.demo.videolibrary.category.CategoryRepository;
import com.demo.videolibrary.exception.DuplicateResourceException;
import com.demo.videolibrary.upload.service.FileUpload;
import com.demo.videolibrary.utils.TypeVideo;
import com.demo.videolibrary.video.Video;
import com.demo.videolibrary.video.VideoRepository;
import com.demo.videolibrary.video.dao.VideoDao;
import com.demo.videolibrary.video.dto.VideoDto;
import com.demo.videolibrary.video.dto.VideoDtoMapper;
import com.demo.videolibrary.video.request.CreateVideoRequest;
import com.demo.videolibrary.video.request.UpdateVideoRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoDao videoDao;
    private final VideoDtoMapper videoDtoMapper;
    private final FileUpload fileUpload;
    private final CategoryRepository categoryRepository;


    @Override
    public List<VideoDto> getVideoList() {
        return videoRepository.findAll()
                .stream()
                .map(videoDtoMapper::toVideoDto)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto getVideoById(String id) {
        return videoRepository.findById(UUID.fromString(id))
                .map(videoDtoMapper::toVideoDto)
                .orElseThrow(() -> {
                    log.error("Video with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Video with id [%s] not found".formatted(id));
                });
    }

    @Override
    public VideoDto addVideo(CreateVideoRequest request, MultipartFile file) throws IOException {
        var title = request.title();
        if (videoDao.existsVideoByTitle(title)) {
            log.error("Video with title [%s] already exists".formatted(title));
            throw new EntityExistsException("Video with name [%s] already exists".formatted(title));
        }


        Category category = categoryRepository.findById(UUID.fromString(request.categoryid())).orElseThrow(() -> {
            log.error("category with id [%s] not found".formatted(request.categoryid()));
            return new EntityNotFoundException(
                    "category with id [%s] not found".formatted(request.categoryid()));
        });

        var fileName = fileUpload.upload(file, "video/");

        Video video = new Video();
        video.setTitle(request.title());
        video.setDescription(request.description());
        video.setUrl(request.url());
        video.setCover(fileName);
        video.setCategory(category);
        video.setVideoType(TypeVideo.valueOf(request.videoType()));
        Video videoSaved = videoRepository.save(video);

        return videoDtoMapper.toVideoDto(videoSaved);

    }

    @Override
    public VideoDto updateVideo(UpdateVideoRequest request, MultipartFile file) throws IOException {
        var id = request.id();

        Video video = videoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Video with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Video with id [%s] not found".formatted(id)
                    );}
                );

        if (videoDao.existsVideoByTitle(request.title())) {
            log.error("Video with name [%s] already exists".formatted(request.title()));
            throw new DuplicateResourceException(
                    "title already taken"
            );
        }

        Category category = categoryRepository.findById(UUID.fromString(request.categoryid())).orElseThrow(() -> {
            log.error("category with id [%s] not found".formatted(request.categoryid()));
            return new EntityNotFoundException(
                    "category with id [%s] not found".formatted(request.categoryid()));
        });


        if (!file.isEmpty()) {
            fileUpload.delete(video.getCover());
            var cover = fileUpload.upload(file, "video/");
            video.setCover(cover);
        }

        video.setTitle(request.title());
        video.setDescription(request.description());
        video.setUrl(request.url());
        video.setCategory(category);
        video.setVideoType(TypeVideo.valueOf(request.videoType()));
        Video videoSaved = videoRepository.save(video);

        return videoDtoMapper.toVideoDto(videoSaved);
    }

    @Override
    public void deleteVideo(String id) {
        Video video = videoRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Video with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Video with id [%s] not found".formatted(id));
                });
        fileUpload.delete(video.getCover());
        videoRepository.delete(video);
    }
}

package com.demo.videolibrary.video.dto;

import com.demo.videolibrary.video.Video;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoDtoMapper  {
    VideoDtoMapper INSTANCE = Mappers.getMapper(VideoDtoMapper.class);
    VideoDto toVideoDto(Video video);
    Video toVideo(VideoDto videoDto);
}

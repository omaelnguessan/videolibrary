package com.demo.videolibrary.playlist.dto;

import com.demo.videolibrary.playlist.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaylistDtoMapper {
    PlaylistDtoMapper INSTANCE = Mappers.getMapper(PlaylistDtoMapper.class);
    Playlist ToPlaylist(PlaylistDto playlistDto);
    PlaylistDto ToPlaylistDto(Playlist playlist);
}

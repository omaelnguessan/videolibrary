package com.demo.videolibrary.playlist.service;

import com.demo.videolibrary.playlist.dto.PlaylistDto;
import com.demo.videolibrary.playlist.request.CreatePlaylistRequest;
import com.demo.videolibrary.playlist.request.UpdatePlaylistRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlaylistService {
    List<PlaylistDto> getPlaylists();
    PlaylistDto getPlaylistById(String id);
    PlaylistDto addPlaylist(CreatePlaylistRequest createPlaylist, MultipartFile file) throws IOException;
    PlaylistDto updatePlaylist(UpdatePlaylistRequest updatePlaylist,  MultipartFile file) throws IOException;
    void deletePlaylist(String id);
}

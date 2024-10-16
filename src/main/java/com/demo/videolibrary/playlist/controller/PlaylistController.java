package com.demo.videolibrary.playlist.controller;

import com.demo.videolibrary.playlist.controller.api.PlaylistApi;
import com.demo.videolibrary.playlist.dto.PlaylistDto;
import com.demo.videolibrary.playlist.request.CreatePlaylistRequest;
import com.demo.videolibrary.playlist.request.UpdatePlaylistRequest;
import com.demo.videolibrary.playlist.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class PlaylistController implements PlaylistApi {

    private final PlaylistService playlistService;

    @Override
    public ResponseEntity<List<PlaylistDto>> getPlaylists() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(playlistService.getPlaylists());
    }

    @Override
    public ResponseEntity<PlaylistDto> getPlaylist(String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(playlistService.getPlaylistById(id));
    }

    @Override
    public ResponseEntity<PlaylistDto> createPlaylist(CreatePlaylistRequest request, MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playlistService.addPlaylist(request, file));
    }

    @Override
    public ResponseEntity<PlaylistDto> updatePlaylist(UpdatePlaylistRequest request, MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playlistService.updatePlaylist(request, file));
    }

    @Override
    public void deletePlaylist(String id) {
        playlistService.deletePlaylist(id);
    }
}

package com.demo.videolibrary.playlist.controller.api;

import com.demo.videolibrary.playlist.dto.PlaylistDto;
import com.demo.videolibrary.playlist.request.CreatePlaylistRequest;
import com.demo.videolibrary.playlist.request.UpdatePlaylistRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.demo.videolibrary.utils.Constants.APP_ROOT_V1;

public interface PlaylistApi {

    @GetMapping(value = APP_ROOT_V1 + "/playlist/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PlaylistDto>> getPlaylists();

    @GetMapping(value = APP_ROOT_V1 + "/playlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlaylistDto> getPlaylist(@PathVariable("id") String id);

    @PostMapping(value = APP_ROOT_V1 + "/playlist/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlaylistDto> createPlaylist(@ModelAttribute CreatePlaylistRequest request, @RequestParam("image") MultipartFile file) throws IOException;

    @PutMapping(value = APP_ROOT_V1 + "/playlist/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PlaylistDto> updatePlaylist(@ModelAttribute UpdatePlaylistRequest request, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException;

    @DeleteMapping(value = APP_ROOT_V1 + "/playlist/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deletePlaylist(@PathVariable("id") String id);
}

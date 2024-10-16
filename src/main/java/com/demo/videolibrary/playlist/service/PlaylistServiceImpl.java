package com.demo.videolibrary.playlist.service;

import com.demo.videolibrary.exception.DuplicateResourceException;
import com.demo.videolibrary.playlist.Playlist;
import com.demo.videolibrary.playlist.PlaylistRepository;
import com.demo.videolibrary.playlist.dao.PlaylistDao;
import com.demo.videolibrary.playlist.dto.PlaylistDto;
import com.demo.videolibrary.playlist.dto.PlaylistDtoMapper;
import com.demo.videolibrary.playlist.request.CreatePlaylistRequest;
import com.demo.videolibrary.playlist.request.UpdatePlaylistRequest;
import com.demo.videolibrary.upload.service.FileUpload;
import com.demo.videolibrary.video.dto.VideoDtoMapper;
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

@Slf4j
@AllArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistDao playlistDao;
    private final PlaylistDtoMapper playlistDtoMapper;
    private final FileUpload fileUpload;
    private final VideoDtoMapper videoDtoMapper;


    @Override
    public List<PlaylistDto> getPlaylists() {
        return this.playlistRepository.findAll()
                .stream()
                .map(playlistDtoMapper::ToPlaylistDto)
                .collect(Collectors.toList());
    }


    @Override
    public PlaylistDto getPlaylistById(String id) {
        return this.playlistRepository.findById(UUID.fromString(id))
                .map(playlistDtoMapper::ToPlaylistDto)
                .orElseThrow(() -> {
                    log.error("Playlist with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Playlist with id [%s] not found".formatted(id));
                });
    }

    @Override
    public PlaylistDto addPlaylist(CreatePlaylistRequest createPlaylist,
                                   MultipartFile file) throws IOException {
        var name = createPlaylist.name();
        if (playlistDao.existsPlaylistByName(name)) {
            log.error("Playlist with name [%s] already exists".formatted(name));
            throw new EntityExistsException("Playlist with name [%s] already exists".formatted(name));
        }

        var fileName = fileUpload.upload(file, "playlist/");

        Playlist playlist = new Playlist();
        playlist.setName(createPlaylist.name());
        playlist.setDescription(createPlaylist.description());
        playlist.setCover(fileName);

        Playlist playlistSaved = playlistRepository.save(playlist);

        return playlistDtoMapper.ToPlaylistDto(playlistSaved);
    }

    @Override
    public PlaylistDto updatePlaylist(UpdatePlaylistRequest updatePlaylist,
                                      MultipartFile file) throws IOException {
        var id = updatePlaylist.id();

        Playlist playlist = playlistRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Playlist with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                                    "Playlist with id [%s] not found".formatted(id)
                    );}
                );

        if (playlistDao.existsPlaylistByName(updatePlaylist.name())) {
            log.error("Playlist with name [%s] already exists".formatted(updatePlaylist.name()));
            throw new DuplicateResourceException(
                    "name already taken"
            );
        }
        playlist.setName(updatePlaylist.name());
        playlist.setDescription(updatePlaylist.description());

        if (!file.isEmpty()) {
            fileUpload.delete(playlist.getCover());
            var cover = fileUpload.upload(file, "playlist/");
            playlist.setCover(cover);
        }

        Playlist playlistSaved = playlistRepository.save(playlist);

        return playlistDtoMapper.ToPlaylistDto(playlistSaved);
    }

    @Override
    public void deletePlaylist(String id) {
        Playlist playlist = playlistRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Playlist with id [%s] not found".formatted(id));
                    return new EntityNotFoundException(
                            "Playlist with id [%s] not found".formatted(id));
                });

        fileUpload.delete(playlist.getCover());

        playlistRepository.delete(playlist);
    }
}

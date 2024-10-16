package com.demo.videolibrary.playlist.dto;

import java.util.UUID;

public record PlaylistDto(
        UUID id,
        String name,
        String description,
        String cover
) {
}

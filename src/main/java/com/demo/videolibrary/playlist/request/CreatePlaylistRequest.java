package com.demo.videolibrary.playlist.request;

public record CreatePlaylistRequest (
        String name,
        String description
) { }

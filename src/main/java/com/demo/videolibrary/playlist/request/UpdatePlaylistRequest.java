package com.demo.videolibrary.playlist.request;

public record UpdatePlaylistRequest (
        String id,
        String name,
        String description
){
}

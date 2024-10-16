package com.demo.videolibrary.video.request;

public record UpdateVideoRequest(
        String id,
        String title,
        String url,
        String description,
        String cover,
        String videoType,
        String categoryid
) {
}

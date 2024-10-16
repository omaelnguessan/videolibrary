package com.demo.videolibrary.video.request;

public record CreateVideoRequest(
        String title,
        String url,
        String description,
        String cover,
        String videoType,
        String categoryid
) {
}

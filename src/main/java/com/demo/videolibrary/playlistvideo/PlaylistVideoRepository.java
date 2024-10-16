package com.demo.videolibrary.playlistvideo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideo, UUID> {
}

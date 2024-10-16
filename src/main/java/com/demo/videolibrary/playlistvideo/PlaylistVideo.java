package com.demo.videolibrary.playlistvideo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Data
@Getter
@NoArgsConstructor
@Setter
@Table(name = "playlistvideos")
public class PlaylistVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID playlistId;
    @Column(nullable = false)
    private UUID videoId;
}

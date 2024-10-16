package com.demo.videolibrary.video;

import com.demo.videolibrary.category.Category;
import com.demo.videolibrary.utils.TypeVideo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Data
@Getter
@NoArgsConstructor
@Setter
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String title;

    @Column(unique = true)
    private String url;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String cover;

    @Column(nullable = true)
    private TypeVideo videoType;

    @ManyToOne
    @JoinColumn(name = "idcategories")
    private Category category;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    private Instant creationAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonIgnore
    private Instant updatedAt;
}

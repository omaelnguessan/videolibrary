package com.demo.videolibrary.category;

import com.demo.videolibrary.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Builder
@Entity
@Data
@Getter
@NoArgsConstructor
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "categories")
    private List<Video> videoList;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    private Instant creationAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonIgnore
    private Instant updatedAt;
}

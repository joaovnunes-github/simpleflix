package com.simple.persistence.metadata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Entity
@Getter
public class Metadata {
    @Id
    private UUID uuid;
    private String title;
    private Long duration;
    @Column(name="media_uuid")
    private String mediaUuid;
    @Column(name="media_preview_uuid")
    private String mediaPreviewUuid;
    @Column(name="image_preview_uuid")
    private String imagePreviewUuid;
}


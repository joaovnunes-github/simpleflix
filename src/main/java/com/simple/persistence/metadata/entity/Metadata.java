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
    @Column(name="media_s3_uuid")
    private String mediaS3Uuid;
    @Column(name="preview_s3_uuid")
    private String previewS3Uuid;
    @Column(name="thumbnail_s3_uuid")
    private String thumbnailS3UUID;
}


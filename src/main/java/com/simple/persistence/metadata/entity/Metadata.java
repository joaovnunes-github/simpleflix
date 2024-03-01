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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Long duration;
    @Column(nullable = false)
    private String s3Path;
}


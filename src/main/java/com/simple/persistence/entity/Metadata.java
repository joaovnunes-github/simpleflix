package com.simple.persistence.entity;

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
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Long duration;
    @Column(nullable = false)
    private String s3Path;
//    private final ResourceLoader resourceLoader;
//    @Autowired
//    public Media(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    public Resource loadLuffyVideo() throws IOException {
//        String fileName = "Luffy_Punches_a_Celestial_Dragon_One_Piece.mp4";
//
//        // Load the resource using ResourceLoader
//        return resourceLoader.getResource("classpath:media/" + fileName);
//    }
}


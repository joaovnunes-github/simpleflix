package com.simple.controller;

import com.simple.persistence.metadata.entity.Metadata;
import com.simple.service.MediaService;
import com.simple.service.MetadataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Slf4j
public class MediaController {
    private final MediaService mediaService;
    private final MetadataService metadataService;

    @Autowired
    public MediaController(MediaService mediaService, MetadataService metadataService) {
        this.mediaService = mediaService;
        this.metadataService = metadataService;
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<Metadata>> getAllMetadata() {
        try {
            return metadataService.getAllMetadata();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/media/{mediaUuid}/stream")
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @PathVariable UUID mediaUuid
    ) {
        try {
            return mediaService.streamMedia(mediaUuid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

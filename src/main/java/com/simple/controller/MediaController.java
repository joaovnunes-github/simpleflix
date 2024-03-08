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

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }
    @GetMapping("/media/{uuid}/stream")
    public ResponseEntity<StreamingResponseBody> streamVideo(
            @PathVariable UUID uuid
    ) {
        try {
            return mediaService.streamMedia(uuid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/media-preview/{uuid}/stream")
    public ResponseEntity<StreamingResponseBody> streamMediaPreview(
            @PathVariable UUID uuid
    ) {
        try {
            return mediaService.streamMediaPreview(uuid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/image-preview/{uuid}/stream")
    public ResponseEntity<StreamingResponseBody> streamImagePreview(
            @PathVariable UUID uuid
    ) {
        try {
            return mediaService.streamImagePreview(uuid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}

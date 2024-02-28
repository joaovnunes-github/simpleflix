package com.simple.controller;

import com.simple.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Replace with your React app's origin
@RequestMapping("/api")
@Slf4j
public class MediaController {
    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }



    @GetMapping("/stream-video")
    public ResponseEntity<StreamingResponseBody> streamVideo() {
        try {
            return mediaService.streamVideo();
        } catch (IOException e) {
            log.error(e.getMessage());
            // Handle exception appropriately (e.g., log it)
            return ResponseEntity.status(500).build();  // Internal Server Error
        }
    }
}

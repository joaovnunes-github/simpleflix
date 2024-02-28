package com.simple.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MediaPersistence {
    private final ResourceLoader resourceLoader;
    @Autowired
    public MediaPersistence(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource loadLuffyVideo() throws IOException {
        String fileName = "Luffy_Punches_a_Celestial_Dragon_One_Piece.mp4";

        // Load the resource using ResourceLoader
        return resourceLoader.getResource("classpath:media/" + fileName);
    }
}

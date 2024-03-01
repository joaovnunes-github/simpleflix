package com.simple.service;

import com.simple.persistence.media.repository.MediaRepository;
import com.simple.strategy.stream.StreamStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public class MediaService {
    private static final long THROTTLE_RATE = 1024 * 512;

    MediaRepository mediaRepository;
    StreamStrategy streamStrategy;

    @Autowired
    public MediaService(MediaRepository mediaRepository, StreamStrategy streamStrategy) {
        this.mediaRepository = mediaRepository;
        this.streamStrategy = streamStrategy;
    }

    public ResponseEntity<StreamingResponseBody> streamMedia(UUID uuid) throws IOException {
        InputStream mediaInputStream = mediaRepository.findBy(uuid);
        StreamingResponseBody streamingResponseBody = streamStrategy.stream(mediaInputStream);

        return ResponseEntity.ok().body(streamingResponseBody);
    }
}

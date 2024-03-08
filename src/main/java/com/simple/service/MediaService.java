package com.simple.service;

import com.simple.persistence.media.repository.S3ImagePreviewRepository;
import com.simple.persistence.media.repository.S3MediaPreviewRepository;
import com.simple.persistence.media.repository.S3MediaRepository;
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

    S3MediaRepository mediaRepository;
    S3MediaPreviewRepository mediaPreviewRepository;
    S3ImagePreviewRepository imagePreviewRepository;
    StreamStrategy streamStrategy;

    @Autowired
    public MediaService(S3MediaRepository mediaRepository, S3MediaPreviewRepository mediaPreviewRepository, S3ImagePreviewRepository imagePreviewRepository, StreamStrategy streamStrategy) {
        this.mediaRepository = mediaRepository;
        this.mediaPreviewRepository = mediaPreviewRepository;
        this.imagePreviewRepository = imagePreviewRepository;
        this.streamStrategy = streamStrategy;
    }

    public ResponseEntity<StreamingResponseBody> streamMedia(UUID uuid) throws IOException {
        InputStream mediaInputStream = mediaRepository.findBy(uuid);
        StreamingResponseBody streamingResponseBody = streamStrategy.stream(mediaInputStream);

        return ResponseEntity.ok().body(streamingResponseBody);
    }

    public ResponseEntity<StreamingResponseBody> streamMediaPreview(UUID uuid) throws IOException {
        InputStream mediaInputStream = mediaPreviewRepository.findBy(uuid);
        StreamingResponseBody streamingResponseBody = streamStrategy.stream(mediaInputStream);

        return ResponseEntity.ok().body(streamingResponseBody);
    }

    public ResponseEntity<StreamingResponseBody> streamImagePreview(UUID uuid) throws IOException {
        InputStream mediaInputStream = imagePreviewRepository.findBy(uuid);
        StreamingResponseBody streamingResponseBody = streamStrategy.stream(mediaInputStream);

        return ResponseEntity.ok().body(streamingResponseBody);
    }
}

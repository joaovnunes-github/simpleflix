package com.simple.service;

import com.simple.persistence.MediaPersistence;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.io.hadoopbackport.ThrottledInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class MediaService {
    private final MediaPersistence mediaPersistence;

    @Autowired
    public MediaService(MediaPersistence mediaPersistence) {
        this.mediaPersistence = mediaPersistence;
    }
    private static final long THROTTLE_RATE = 1024 * 512;
    public ResponseEntity<StreamingResponseBody> streamVideo() throws IOException {
        Resource videoResource = mediaPersistence.loadLuffyVideo();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        // headers.setExpires(1);
        headers.setContentLength(videoResource.contentLength());
        // Use ThrottledInputStream to throttle the video stream
        InputStream throttledInputStream = new ThrottledInputStream(videoResource.getInputStream(), 1024 * 1024);
            StreamingResponseBody stream = outputStream -> {
                throttledInputStream.transferTo(outputStream);
                outputStream.close();
                log.info("Released Stream");
            };

            // Return the ResponseEntity with the InputStreamResource
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(stream);
    }
}

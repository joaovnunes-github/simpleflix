package com.simple.service;

import com.simple.persistence.entity.Metadata;
import com.simple.persistence.repository.MetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MediaService {
    private static final long THROTTLE_RATE = 1024 * 512;

    MetadataRepository metadataRepository;

    @Autowired
    public MediaService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public ResponseEntity<List<Metadata>> getAllMetadata() {
        List<Metadata> metadata = metadataRepository.findAll();

        return ResponseEntity.ok()
                .body(metadata);
    }

    public ResponseEntity<StreamingResponseBody> streamVideo() throws IOException {
//        Resource videoResource = new Object();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("video/mp4"));
//        // headers.setExpires(1);
//        headers.setContentLength(videoResource.contentLength());
//        // Use ThrottledInputStream to throttle the video stream
//        InputStream throttledInputStream = new ThrottledInputStream(videoResource.getInputStream(), 1024 * 1024);
//            StreamingResponseBody stream = outputStream -> {
//                throttledInputStream.transferTo(outputStream);
//                outputStream.close();
//                log.info("Released Stream");
//            };
//
//            // Return the ResponseEntity with the InputStreamResource
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(stream);
        return null;
    }
}

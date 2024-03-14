package com.simple.service;

import com.simple.persistence.media.repository.S3ImagePreviewRepository;
import com.simple.persistence.media.repository.S3MediaPreviewRepository;
import com.simple.persistence.media.repository.S3MediaRepository;
import com.simple.strategy.stream.StreamStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MediaService Test")
class MediaServiceTest {
    @Mock
    S3MediaRepository s3MediaRepository;
    @Mock
    S3MediaPreviewRepository s3MediaPreviewRepository;
    @Mock
    S3ImagePreviewRepository s3ImagePreviewRepository;
    @Mock
    private StreamStrategy streamStrategy;

    @InjectMocks
    private MediaService mediaService;

    @Test
    @DisplayName("Should stream media successfully")
    void streamMedia_ShouldStreamMediaSuccessfully() throws IOException {
        UUID uuid = UUID.randomUUID();
        InputStream mediaInputStream = new ByteArrayInputStream("Test content".getBytes());
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);

        when(s3MediaRepository.findBy(uuid)).thenReturn(mediaInputStream);
        when(streamStrategy.stream(mediaInputStream)).thenReturn(streamingResponseBody);

        ResponseEntity<StreamingResponseBody> result = mediaService.streamMedia(uuid);

        assertEquals(ResponseEntity.ok().body(streamingResponseBody), result);
        verify(s3MediaRepository, times(1)).findBy(uuid);
        verify(streamStrategy, times(1)).stream(mediaInputStream);
    }

    @Test
    @DisplayName("Should stream media preview successfully")
    void streamMediaPreview_ShouldStreamMediaPreviewSuccessfully() throws IOException {
        UUID uuid = UUID.randomUUID();
        InputStream mediaInputStream = new ByteArrayInputStream("Test content".getBytes());
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);

        when(s3MediaPreviewRepository.findBy(uuid)).thenReturn(mediaInputStream);
        when(streamStrategy.stream(mediaInputStream)).thenReturn(streamingResponseBody);

        ResponseEntity<StreamingResponseBody> result = mediaService.streamMediaPreview(uuid);

        assertEquals(ResponseEntity.ok().body(streamingResponseBody), result);
        verify(s3MediaPreviewRepository, times(1)).findBy(uuid);
        verify(streamStrategy, times(1)).stream(mediaInputStream);
    }

    @Test
    @DisplayName("Should stream image preview successfully")
    void streamImagePreview_ShouldStreamImagePreviewSuccessfully() throws IOException {
        UUID uuid = UUID.randomUUID();
        InputStream mediaInputStream = new ByteArrayInputStream("Test content".getBytes());
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);

        when(s3ImagePreviewRepository.findBy(uuid)).thenReturn(mediaInputStream);
        when(streamStrategy.stream(mediaInputStream)).thenReturn(streamingResponseBody);

        ResponseEntity<StreamingResponseBody> result = mediaService.streamImagePreview(uuid);

        assertEquals(ResponseEntity.ok().body(streamingResponseBody), result);
        verify(s3ImagePreviewRepository, times(1)).findBy(uuid);
        verify(streamStrategy, times(1)).stream(mediaInputStream);
    }
}

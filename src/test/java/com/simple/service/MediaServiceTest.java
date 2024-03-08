package com.simple.service;

import com.simple.persistence.media.repository.S3Repository;
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
    private S3Repository s3Repository;

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

        when(s3Repository.findBy(uuid)).thenReturn(mediaInputStream);
        when(streamStrategy.stream(mediaInputStream)).thenReturn(streamingResponseBody);

        ResponseEntity<StreamingResponseBody> result = mediaService.streamMedia(uuid);

        assertEquals(ResponseEntity.ok().body(streamingResponseBody), result);
        verify(s3Repository, times(1)).findBy(uuid);
        verify(streamStrategy, times(1)).stream(mediaInputStream);
    }
}

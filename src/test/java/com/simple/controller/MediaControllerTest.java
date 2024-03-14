package com.simple.controller;

import com.simple.service.MediaService;
import com.simple.service.MetadataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MediaController Test")
class MediaControllerTest {

    @Mock
    private MediaService mediaService;

    @Mock
    private MetadataService metadataService;

    @InjectMocks
    private MediaController mediaController;

    @Test
    @DisplayName("Should stream video successfully")
    void streamVideo_ShouldStreamVideoSuccessfully() throws IOException {
        UUID mediaUuid = UUID.randomUUID();
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);
        when(mediaService.streamMedia(mediaUuid)).thenReturn(ResponseEntity.ok(streamingResponseBody));

        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamVideo(mediaUuid);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(streamingResponseBody, responseEntity.getBody());
        verify(mediaService, times(1)).streamMedia(mediaUuid);
    }

    @Test
    @DisplayName("Should handle IO exception and return 500 when streaming video")
    void streamVideo_ShouldHandleIOExceptionAndReturn500WhenStreamingVideo() throws IOException {
        UUID mediaUuid = UUID.randomUUID();
        when(mediaService.streamMedia(mediaUuid)).thenThrow(new IOException("Simulating an IO exception"));

        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamVideo(mediaUuid);

        assertEquals(500, responseEntity.getStatusCodeValue());
        verify(mediaService, times(1)).streamMedia(mediaUuid);
    }

    @Test
    @DisplayName("Should stream media preview successfully")
    void streamMediaPreview_ShouldStreamMediaPreviewSuccessfully() throws IOException {
        // Test setup
        UUID mediaUuid = UUID.randomUUID();
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);
        when(mediaService.streamMediaPreview(mediaUuid)).thenReturn(ResponseEntity.ok(streamingResponseBody));

        // Method under test
        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamMediaPreview(mediaUuid);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(streamingResponseBody, responseEntity.getBody());
        verify(mediaService, times(1)).streamMediaPreview(mediaUuid);
    }

    @Test
    @DisplayName("Should handle IO exception and return 500 when streaming media preview")
    void streamMediaPreview_ShouldHandleIOExceptionAndReturn500WhenStreamingMediaPreview() throws IOException {
        // Test setup
        UUID mediaUuid = UUID.randomUUID();
        when(mediaService.streamMediaPreview(mediaUuid)).thenThrow(new IOException("Simulating an IO exception"));

        // Method under test
        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamMediaPreview(mediaUuid);

        // Assertions
        assertEquals(500, responseEntity.getStatusCodeValue());
        verify(mediaService, times(1)).streamMediaPreview(mediaUuid);
    }

    @Test
    @DisplayName("Should stream image preview successfully")
    void streamImagePreview_ShouldStreamImagePreviewSuccessfully() throws IOException {
        // Test setup
        UUID mediaUuid = UUID.randomUUID();
        StreamingResponseBody streamingResponseBody = mock(StreamingResponseBody.class);
        when(mediaService.streamImagePreview(mediaUuid)).thenReturn(ResponseEntity.ok(streamingResponseBody));

        // Method under test
        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamImagePreview(mediaUuid);

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(streamingResponseBody, responseEntity.getBody());
        verify(mediaService, times(1)).streamImagePreview(mediaUuid);
    }

    @Test
    @DisplayName("Should handle IO exception and return 500 when streaming image preview")
    void streamImagePreview_ShouldHandleIOExceptionAndReturn500WhenStreamingImagePreview() throws IOException {
        // Test setup
        UUID mediaUuid = UUID.randomUUID();
        when(mediaService.streamImagePreview(mediaUuid)).thenThrow(new IOException("Simulating an IO exception"));

        // Method under test
        ResponseEntity<StreamingResponseBody> responseEntity = mediaController.streamImagePreview(mediaUuid);

        // Assertions
        assertEquals(500, responseEntity.getStatusCodeValue());
        verify(mediaService, times(1)).streamImagePreview(mediaUuid);
    }
}

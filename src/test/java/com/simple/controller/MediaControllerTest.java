package com.simple.controller;

import com.simple.persistence.metadata.entity.Metadata;
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
import java.util.List;
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
    @DisplayName("Should return all metadata when successful")
    void getAllMetadata_ShouldReturnAllMetadataWhenSuccessful() {
        List<Metadata> metadataList = List.of(new Metadata(), new Metadata());
        when(metadataService.getAllMetadata()).thenReturn(ResponseEntity.ok(metadataList));

        ResponseEntity<List<Metadata>> responseEntity = mediaController.getAllMetadata();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(metadataList, responseEntity.getBody());
        verify(metadataService, times(1)).getAllMetadata();
    }

    @Test
    @DisplayName("Should handle exception and return 500 when getting all metadata")
    void getAllMetadata_ShouldHandleExceptionAndReturn500WhenGettingAllMetadata() {
        when(metadataService.getAllMetadata()).thenThrow(new RuntimeException("Simulating an exception"));

        ResponseEntity<List<Metadata>> responseEntity = mediaController.getAllMetadata();

        assertEquals(500, responseEntity.getStatusCodeValue());
        verify(metadataService, times(1)).getAllMetadata();
    }

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
}

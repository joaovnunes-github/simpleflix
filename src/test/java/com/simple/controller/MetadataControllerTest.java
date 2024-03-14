package com.simple.controller;

import com.simple.persistence.metadata.entity.Metadata;
import com.simple.service.MetadataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Metadata Controller Test")
public class MetadataControllerTest {
    @Mock
    private MetadataService metadataService;
    @InjectMocks
    private MetadataController metadataController;

    @Test
    @DisplayName("Should return all metadata when successful")
    void getAllMetadata_ShouldReturnAllMetadataWhenSuccessful() {
        List<Metadata> metadataList = List.of(new Metadata(), new Metadata());
        when(metadataService.getAllMetadata()).thenReturn(ResponseEntity.ok(metadataList));

        ResponseEntity<List<Metadata>> responseEntity = metadataController.getAllMetadata();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(metadataList, responseEntity.getBody());
        verify(metadataService, times(1)).getAllMetadata();
    }

    @Test
    @DisplayName("Should handle exception and return 500 when getting all metadata")
    void getAllMetadata_ShouldHandleExceptionAndReturn500WhenGettingAllMetadata() {
        when(metadataService.getAllMetadata()).thenThrow(new RuntimeException("Simulating an exception"));

        ResponseEntity<List<Metadata>> responseEntity = metadataController.getAllMetadata();

        assertEquals(500, responseEntity.getStatusCodeValue());
        verify(metadataService, times(1)).getAllMetadata();
    }
}

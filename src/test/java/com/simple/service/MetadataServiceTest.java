package com.simple.service;

import com.simple.persistence.metadata.entity.Metadata;
import com.simple.persistence.metadata.repository.MetadataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MetadataService Test")
class MetadataServiceTest {

    @Mock
    private MetadataRepository metadataRepository;

    @InjectMocks
    private MetadataService metadataService;

    @Test
    @DisplayName("Should get all metadata successfully")
    void getAllMetadata_ShouldGetAllMetadataSuccessfully() {
        List<Metadata> metadataList = Collections.singletonList(new Metadata());

        when(metadataRepository.findAll()).thenReturn(metadataList);

        ResponseEntity<List<Metadata>> result = metadataService.getAllMetadata();

        assertEquals(ResponseEntity.ok().body(metadataList), result);
        verify(metadataRepository, times(1)).findAll();
    }
}

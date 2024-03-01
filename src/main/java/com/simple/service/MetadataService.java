package com.simple.service;

import com.simple.persistence.metadata.entity.Metadata;
import com.simple.persistence.metadata.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataService {
    MetadataRepository metadataRepository;

    @Autowired
    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public ResponseEntity<List<Metadata>> getAllMetadata() {
        List<Metadata> metadata = metadataRepository.findAll();

        return ResponseEntity.ok()
                .body(metadata);
    }
}

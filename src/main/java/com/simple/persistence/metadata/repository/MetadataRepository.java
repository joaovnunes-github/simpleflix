package com.simple.persistence.metadata.repository;

import com.simple.persistence.metadata.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {
    List<Metadata> findAll();
}

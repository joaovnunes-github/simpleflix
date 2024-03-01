package com.simple.persistence.media.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.util.UUID;

@Repository
public class S3MediaRepository implements MediaRepository {
    S3Client s3Client;

    @Autowired
    public S3MediaRepository(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public InputStream findBy(UUID uuid) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("simpleflix")
                .key("media/" + uuid.toString())
                .build();
        return s3Client.getObject(getObjectRequest);
    }
}

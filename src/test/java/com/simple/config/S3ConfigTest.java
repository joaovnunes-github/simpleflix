package com.simple.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.S3Client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = S3Config.class)
class S3ConfigTest {

    @Autowired
    private S3Client s3Client;

    @Test
    @DisplayName("Spring should make the S3Client bean available")
    void testS3ClientBean() {
        assertNotNull(s3Client);
    }
}

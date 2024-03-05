package com.simple.persistence.media.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.http.AbortableInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("S3MediaRepository Test")
class S3MediaRepositoryTest {

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private S3MediaRepository s3MediaRepository;

    @Test
    @DisplayName("Should retrieve InputStream for given UUID")
    void findBy_ShouldRetrieveInputStreamForGivenUUID() {
        UUID uuid = UUID.randomUUID();
        InputStream responseInputStream = new ResponseInputStream<>(
                GetObjectResponse.builder().build(), AbortableInputStream.create(new ByteArrayInputStream(new byte[]{})));

        when(s3Client.getObject(ArgumentMatchers.any(GetObjectRequest.class)))
                .then(
                        invocation -> {
                            GetObjectRequest getObjectRequest = invocation.getArgument(0);
                            assertEquals(getObjectRequest.key(), "media/" + uuid);

                            return responseInputStream;
                        });

        InputStream result = s3MediaRepository.findBy(uuid);
        assertEquals(responseInputStream, result);
    }
}

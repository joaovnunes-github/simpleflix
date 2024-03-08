package com.simple.persistence.media.repository;

import java.io.InputStream;
import java.util.UUID;

public interface S3Repository {
    InputStream findBy(UUID uuid);
}

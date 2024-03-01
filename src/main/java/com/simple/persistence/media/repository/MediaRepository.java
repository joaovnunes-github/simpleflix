package com.simple.persistence.media.repository;

import java.io.InputStream;
import java.util.UUID;

public interface MediaRepository {
    InputStream findBy(UUID uuid);
}

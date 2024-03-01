package com.simple.strategy.stream;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

public interface StreamStrategy {
    public StreamingResponseBody stream(InputStream inputStream);
}

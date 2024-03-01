package com.simple.strategy.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

@Slf4j
public class StandardStream implements StreamStrategy {
    public StreamingResponseBody stream(InputStream inputStream) {
        return outputStream -> {
            inputStream.transferTo(outputStream);
            outputStream.close();
            log.info("Released Stream");
        };
    }
}

package com.simple.strategy.stream;

import com.simple.util.ThrottledInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;

@Slf4j
public class ThrottledStream implements StreamStrategy {

    public StreamingResponseBody stream(InputStream inputStream) {
        long MAX_BYTES_PER_SECOND = 1024 * 1024;
        ThrottledInputStream throttledInputStream = new ThrottledInputStream(inputStream, MAX_BYTES_PER_SECOND);

        return outputStream -> {
            throttledInputStream.transferTo(outputStream);
            outputStream.close();
            log.info("Released Stream");
        };
    }
}

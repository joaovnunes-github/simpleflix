package com.simple.config;

import com.simple.strategy.stream.StandardStream;
import com.simple.strategy.stream.StreamStrategy;
import com.simple.strategy.stream.ThrottledStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class Stream {
    Environment environment;

    @Autowired
    public Stream(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public StreamStrategy streamStrategy() {
        boolean throttled = environment.containsProperty("THROTTLED");
        if (throttled) {
            log.info("Utilizing throttled stream strategy.");
            return new ThrottledStream();
        }

        log.info("Utilizing standard stream strategy.");
        return new StandardStream();
    }
}

package com.simple.config;

import com.simple.strategy.stream.StandardStream;
import com.simple.strategy.stream.StreamStrategy;
import com.simple.strategy.stream.ThrottledStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Stream.class)
class StreamTest {

    @Autowired
    private Stream stream;

    @Test
    @DisplayName("Should create Standard StreamStrategy bean when THROTTLED property is false")
    void testStreamStrategyBeanStandard() {
        // Arrange
        Environment environment = mock(Environment.class);
        when(environment.containsProperty("THROTTLED")).thenReturn(false);

        // Act
        StreamStrategy streamStrategy = stream.streamStrategy();

        // Assert
        assertNotNull(streamStrategy);
        assert streamStrategy instanceof StandardStream;
    }

    @Test
    @DisplayName("Should create ThrottledStreamStrategy bean when THROTTLED property is true")
    void testStreamStrategyBeanThrottled() {
        // Arrange
        Environment environment = mock(Environment.class);
        when(environment.containsProperty("THROTTLED")).thenReturn(true);

        // Act
        StreamStrategy streamStrategy = stream.streamStrategy();

        // Assert
        assertNotNull(streamStrategy);
        assert streamStrategy instanceof ThrottledStream;
    }

    @Test
    @DisplayName("Should have available Stream bean")
    void testStreamBeanAvailability() {
        // Assert
        assertNotNull(stream);
    }
}

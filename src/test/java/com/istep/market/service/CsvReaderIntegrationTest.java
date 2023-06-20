package com.istep.market.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CsvReaderIntegrationTest {

    @Mock
    private Logger logger;

    private CsvReader csvReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        csvReader = new CsvReader();
        csvReader.logger = logger;
    }

    @Test
    public void testRead() {
        // Perform the read operation
        List<List<String>> lines = csvReader.read();

        // Verify the lines
        assertEquals(5, lines.size());
        assertEquals(Arrays.asList("106", "EUR/USD", "1.1000", "1.2000", "01-06-2020 12:01:01:001"), lines.get(0));
        assertEquals(Arrays.asList("107", "EUR/JPY", "119.60", "119.90", "01-06-2020 12:01:02:002"), lines.get(1));
        assertEquals(Arrays.asList("108", "GBP/USD", "1.2500", "1.2560", "01-06-2020 12:01:02:002"), lines.get(2));
        assertEquals(Arrays.asList("109", "GBP/USD", "1.2499", "1.2561", "01-06-2020 12:01:02:100"), lines.get(3));
        assertEquals(Arrays.asList("110", "EUR/JPY", "119.61", "119.91", "01-06-2020 12:01:02:110"), lines.get(4));

        // Verify that logger.info() was not called
        verify(logger, never()).error(anyString());
    }
}

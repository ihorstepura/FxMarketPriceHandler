package com.istep.market.service;

import com.istep.market.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CsvReader {

    Logger logger = LoggerFactory.getLogger(CsvReader.class);

    public List<List<String>> read() {

        String filename = "marketPriceFeed.csv";

        List<List<String>> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader((
                new InputStreamReader(Objects.requireNonNull(CsvReader.class.getClassLoader()
                        .getResourceAsStream(filename)))))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split(StringUtils.COMMA);
                lines.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            logger.error("Failed to read file " + filename);
        }

        return lines;
    }
}

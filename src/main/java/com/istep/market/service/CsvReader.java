package com.istep.market.service;

import com.istep.market.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public final class CsvReader {

    public List<List<String>> read() {

        List<List<String>> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader((
                new InputStreamReader(Objects.requireNonNull(CsvReader.class.getClassLoader()
                        .getResourceAsStream("marketPriceFeed.csv")))))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split(StringUtils.COMMA);
                lines.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            // It is better to go with specific custom exception
            throw new RuntimeException(e);
        }

        return lines;
    }
}

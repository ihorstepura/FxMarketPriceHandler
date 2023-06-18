package com.istep.market.service;

import com.istep.market.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public final class CsvReader {

    public List<List<String>> read() {

        List<List<String>> lines = new ArrayList<>();
        // file path should be replaced with path from .properties
        try (BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\Engineer\\IdeaProjects\\FxMarket\\src\\main\\resources\\templates\\marketPriceFeed.csv"))) {
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

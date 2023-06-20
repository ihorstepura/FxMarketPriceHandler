package com.istep.market.service;

import com.istep.market.entity.Price;
import com.istep.market.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MarketServiceIntegrationTest {

    private MarketService marketService;

    @BeforeEach
    public void setUp() {
        CsvReader csvReader = new CsvReader();
        PriceMapper mapper = new PriceMapper();
        MarginCalculator marginCalculator = new MarginCalculator();
        marketService = new MarketService(mapper, marginCalculator, csvReader);
    }

    @Test
    public void testRead() {
        // Perform the read operation
        Optional<Price> latestPrice = marketService.getLatestPrice("EUR/JPY");

        Price expected = Price.builder()
                .id(110)
                .instrumentName("EUR/JPY")
                .bid(119.49039)
                .ask(120.02991)
                .timestamp("01-06-2020 12:01:02:110")
                .build();


        // Verify the lines
        assertEquals(expected, latestPrice.get());
        System.out.println(latestPrice.get());
    }
}

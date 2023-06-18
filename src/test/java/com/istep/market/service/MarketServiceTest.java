package com.istep.market.service;

import com.istep.market.entity.Price;
import com.istep.market.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MarketServiceTest {

    @Mock
    private PriceMapper priceMapper;

    @Mock
    private MarginCalculator marginCalculator;

    @Mock
    private CsvReader csvReader;

    private MarketService marketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        marketService = new MarketService(priceMapper, marginCalculator, csvReader);
    }

    @Test
    void getLatestPrice() {
        // Prepare test data
        List<String> firstMarketPriceRow = List.of("105", "EUR/USD", "1.1000", "1.2000", "01-06-2020 12:01:01:001");
        List<List<String>> csvRecords = List.of(firstMarketPriceRow);
        Price expectedPrice = Price.builder()
                .id(106)
                .instrumentName("EUR/USD")
                .bid(1.0989)
                .ask(1.21012)
                .timestamp("01-06-2020 12:01:01:0011")
                .build();
        List<Price> expectedPrices = Collections.singletonList(expectedPrice);

        when(csvReader.read()).thenReturn(csvRecords);
        when(priceMapper.mapRecordToPrice(csvRecords.get(0))).thenReturn(expectedPrices.get(0));
        when(marginCalculator.addCommission(expectedPrices.get(0))).thenReturn(expectedPrices.get(0));

        // Perform the service method invocation
        Price actualPrice = marketService.getLatestPrice();

        // Verify the result
        assertEquals(expectedPrices.get(0), actualPrice);
    }
}

package com.istep.market.service;

import com.istep.market.entity.Price;
import com.istep.market.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MarketServiceUnitTest {

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
        String priceName = "EUR/USD";
        List<String> firstMarketPriceRow = List.of("105", "EUR/USD", "1.1000", "1.2000", "01-06-2020 12:01:01:001");
        List<String> latestMarketPriceRow = List.of("106", "EUR/USD", "1.2000", "1.3000", "01-06-2020 12:01:01:005");
        List<List<String>> csvRecords = List.of(firstMarketPriceRow);
        Price expectedPrice = Price.builder()
                .id(106)
                .instrumentName(priceName)
                .bid(1.1988)
                .ask(1.3013)
                .timestamp("01-06-2020 12:01:01:005")
                .build();
        List<Price> expectedPrices = Collections.singletonList(expectedPrice);

        when(csvReader.read()).thenReturn(csvRecords);
        when(priceMapper.mapRecordToPrice(csvRecords.get(0))).thenReturn(expectedPrices.get(0));
        when(marginCalculator.addCommission(expectedPrices.get(0))).thenReturn(expectedPrices.get(0));

        // Perform the service method invocation
        Optional<Price> actualPrice = marketService.getLatestPrice(priceName);

        // Verify the result
        assertEquals(expectedPrices.get(0), actualPrice.get());
    }
}

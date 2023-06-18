package com.istep.market.service;

import com.istep.market.entity.Price;
import com.istep.market.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final PriceMapper mapper;

    private final MarginCalculator marginCalculator;

    private final CsvReader csvReader;

    public Price getLatestPrice() {
        List<Price> prices = csvReader.read().stream()
                .map(mapper::mapRecordToPrice)
                .map(marginCalculator::addCommission)
                .collect(Collectors.toList());
        Collections.reverse(prices);
        return prices.stream().findFirst().orElseThrow();
    }
}

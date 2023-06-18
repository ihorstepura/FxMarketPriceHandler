package com.istep.market.mapper;

import com.istep.market.entity.Price;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceMapper {

    public Price mapRecordToPrice(List<String> records) {
        return Price.builder()
                .id(Long.parseLong(records.get(0)))
                .instrumentName(records.get(1))
                .bid(Double.parseDouble(records.get(2)))
                .ask(Double.parseDouble(records.get(3)))
                .timestamp(records.get(4))
                .build();
    }
}

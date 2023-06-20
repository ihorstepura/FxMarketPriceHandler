package com.istep.market.service;

import com.istep.market.entity.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarginCalculatorUnitTest {

    @Test
    void addCommission() {
        // Prepare test data
        double bid = 1.1000;
        double ask = 1.2000;

        Price price = Price.builder()
                .bid(bid)
                .ask(ask)
                .build();

        double expectedBidWithCommission = bid - (bid / 100.0 * 0.1);
        double expectedAskWithCommission = ask + (ask / 100.0 * 0.1);

        MarginCalculator marginCalculator = new MarginCalculator();

        // Perform the calculation
        Price result = marginCalculator.addCommission(price);

        // Verify the result
        assertEquals(expectedBidWithCommission, result.getBid(), 0.001);
        assertEquals(expectedAskWithCommission, result.getAsk(), 0.001);
    }
}
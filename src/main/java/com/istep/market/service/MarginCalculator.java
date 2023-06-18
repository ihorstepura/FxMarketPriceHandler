package com.istep.market.service;

import com.istep.market.entity.Price;
import org.springframework.stereotype.Component;

@Component
public class MarginCalculator {

    public Price addCommission(Price price) {

        double PERCENT = 100.0;
        double COMMISSION = 0.1;

        // commission on bid is -0.1%
        double bidWithCommission = price.getBid() - (price.getBid() / PERCENT * COMMISSION);
        price.setBid(bidWithCommission);
        // commission on ask is +0.1%
        double askWithCommission = price.getAsk() + (price.getAsk() / PERCENT * COMMISSION);
        price.setAsk(askWithCommission);

        return price;
    }
}

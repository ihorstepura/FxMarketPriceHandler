package com.istep.market.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Price {

    private long id;
    private String instrumentName;
    private double bid;
    private double ask;
    // should be changed to Timestamp/Date
    private String timestamp;

}

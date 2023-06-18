package com.istep.market.controller;

import com.istep.market.entity.Price;
import com.istep.market.service.MarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MarketControllerTest {

    @Mock
    private MarketService marketService;

    private MarketController marketController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        marketController = new MarketController(marketService);
    }

    @Test
    public void getLatestPrice() {
        // Prepare test data
        Price expectedPrice = Price.builder()
                .id(1)
                .instrumentName("Name")
                .bid(1.0)
                .ask(1.1)
                .timestamp("01-06-2020 12:01:01:001")
                .build();
        when(marketService.getLatestPrice()).thenReturn(expectedPrice);

        // Perform the controller method invocation
        ResponseEntity<Price> responseEntity = marketController.getLatestPrice();

        // Verify the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify the response body
        Price actualPrice = responseEntity.getBody();
        assertEquals(expectedPrice, actualPrice);
    }
}
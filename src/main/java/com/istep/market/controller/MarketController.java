package com.istep.market.controller;

import com.istep.market.entity.Price;
import com.istep.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("price")
    public ResponseEntity<Price> getLatestPrice() {
        return new ResponseEntity<>(marketService.getLatestPrice(), HttpStatus.OK);
    }
}

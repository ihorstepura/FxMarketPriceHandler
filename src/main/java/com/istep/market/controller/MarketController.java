package com.istep.market.controller;

import com.istep.market.entity.Price;
import com.istep.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("price")
    public ResponseEntity<Optional<Price>> getLatestPrice(@RequestParam String priceFeed) {
        Optional<Price> latestPrice = marketService.getLatestPrice(priceFeed);
        return latestPrice.isPresent() ?
                new ResponseEntity<>(latestPrice, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.ecommerce.prices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.prices.model.Price;
import com.ecommerce.prices.services.PriceService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchPrices(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        
            Optional<Price> priceOptional = priceService.searchPrices(applicationDate, productId, brandId);
        
            return priceOptional.map(price -> {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("productId", price.getProductId());
                responseMap.put("brandId", price.getBrandId()); 
                responseMap.put("priceList", price.getPriceList());
                responseMap.put("startDate", price.getStartDate().toString());
                responseMap.put("endDate", price.getEndDate());
                responseMap.put("price", price.getPrice());
           
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        Price savedPrice = priceService.savePrice(price);
        return new ResponseEntity<>(savedPrice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Price>> getAllPrices() {
        List<Price> prices = priceService.getAllPrices();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable Long id) {
        Price price = priceService.getPriceById(id);
        if (price != null) {
            return new ResponseEntity<>(price, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        Price price = priceService.getPriceById(id);
        if (price != null) {
            priceService.deletePrice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


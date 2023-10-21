package com.ecommerce.prices.services;

import java.util.List;
import java.util.Optional;

import com.ecommerce.prices.model.Price;

public interface PriceService {
    Price savePrice(Price price);

    List<Price> getAllPrices();

    Price getPriceById(Long id);

    void deletePrice(Long id);

    Optional<Price> searchPrices(String applicationDate, Long productId, Long brandId);
}

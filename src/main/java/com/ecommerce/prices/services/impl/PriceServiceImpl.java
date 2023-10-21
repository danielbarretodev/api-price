package com.ecommerce.prices.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.prices.model.Price;
import com.ecommerce.prices.repository.PriceRepository;
import com.ecommerce.prices.services.PriceService;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePrice(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public Optional<Price> searchPrices(String applicationDate, Long productId, Long brandId) {
    
        List<Price> prices = priceRepository.searchPrices(stringToTimestamp(applicationDate), productId, brandId);

        return prices.stream()
        .max(Comparator.comparing(Price::getPriority));


    }

    private Timestamp stringToTimestamp(String dateString) {
        
        dateString = dateString.replace("-", " ").replace(".", ":");
 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        Timestamp timestamp = null;
        try {
            Date date = sdf.parse(dateString);
            timestamp = new Timestamp(date.getTime());

           
        } catch (ParseException e) {
            e.printStackTrace();
        }

         return timestamp;
    }
}


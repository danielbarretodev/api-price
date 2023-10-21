package com.ecommerce.prices.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.prices.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

  
    // usamos la anotación @Query para consultas personalizadas
    @Query("SELECT p FROM Price p WHERE :applicationDate >= p.startDate " +
           "AND :applicationDate <= p.endDate " +
           "AND p.productId = :productId " +
           "AND p.brandId = :brandId")
    List<Price> searchPrices(@Param("applicationDate") Timestamp applicationDate,
                            @Param("productId") Long productId,
                            @Param("brandId") Long brandId);

  
}
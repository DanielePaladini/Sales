package com.daniele.salestaxes.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxCalculationService {

    private final String INTERVAL =  "0.05";

    public BigDecimal calculate(BigDecimal price, BigDecimal percentage){

        BigDecimal tax = price.multiply(percentage).divide(new BigDecimal("100"));
        return roudUp(tax);
    }

    private BigDecimal roudUp(BigDecimal taxAmount){

        BigDecimal interval = new BigDecimal(INTERVAL);
        BigDecimal roundedUpAmount = taxAmount.divide(interval, RoundingMode.UP);
        roundedUpAmount = roundedUpAmount.setScale(0, RoundingMode.UP);
        return roundedUpAmount.multiply(interval).setScale(2, RoundingMode.UP);
    }
}

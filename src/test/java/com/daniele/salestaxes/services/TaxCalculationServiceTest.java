package com.daniele.salestaxes.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaxCalculationServiceTest {


    BigDecimal baseTax = new BigDecimal("10");
    BigDecimal importedTax = new BigDecimal("5");
    TaxCalculationService calculationService = new TaxCalculationService();

    @Test
    public void testInput1(){
        BigDecimal prize = new BigDecimal("14.99");

        assertEquals("1.50", calculationService.calculate(prize, baseTax).toString());
    }

    @Test
    public void testInput2(){
        BigDecimal imprtChocolatePrice = new BigDecimal("10");
        BigDecimal imprtPerfume = new BigDecimal("47.50");

        assertEquals("0.50", calculationService.calculate(imprtChocolatePrice, importedTax).toString());
        assertEquals("2.40", calculationService.calculate(imprtPerfume, importedTax).toString());
        assertEquals("4.75", calculationService.calculate(imprtPerfume, baseTax).toString());
    }

    @Test
    public void testInput3(){
        BigDecimal imprtPerfume = new BigDecimal("27.99");
        BigDecimal perfume = new BigDecimal("18.99");
        BigDecimal imprtChocolate = new BigDecimal("11.25");

        assertEquals("1.40", calculationService.calculate(imprtPerfume, importedTax).toString());
        assertEquals("2.80", calculationService.calculate(imprtPerfume, baseTax).toString());
        assertEquals("1.90", calculationService.calculate(perfume, baseTax).toString());
        assertEquals("0.60", calculationService.calculate(imprtChocolate, importedTax).toString());
    }
}
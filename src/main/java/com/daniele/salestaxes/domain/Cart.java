package com.daniele.salestaxes.domain;

import com.daniele.salestaxes.domain.goods.Exempted;
import com.daniele.salestaxes.domain.goods.Item;
import com.daniele.salestaxes.services.TaxCalculationService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private TaxCalculationService taxCalculationService;
    private List<Item> itemList;

    public Cart(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
        itemList = new ArrayList<>();
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public Receipt checkOut(){
        List<Entry> entries = new ArrayList<>();
        for (Item item: itemList) {
            BigDecimal importationTaxes = new BigDecimal("0");
            BigDecimal baseTaxes = new BigDecimal("0");

            if (item.getIsImported()){
                importationTaxes = taxCalculationService.calculate(item.getPrice(), new BigDecimal("5"));
            }
            if (!(item instanceof Exempted)){
                baseTaxes = taxCalculationService.calculate(item.getPrice(), new BigDecimal("10"));
            }
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            BigDecimal totalItemTaxes = baseTaxes.add(importationTaxes);
            BigDecimal totalItemPrice = item.getPrice().add(totalItemTaxes).multiply(quantity);

            Entry entry = new Entry(item, totalItemPrice, totalItemTaxes);
            entries.add(entry);
        }
        return new Receipt(entries);
    }

    public void discardAll(){ itemList = new ArrayList<>(); }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        itemList.forEach(item -> sb.append(item + " at " + item.getPrice().toString()+ "\n"));
        return sb.toString();
    }
}

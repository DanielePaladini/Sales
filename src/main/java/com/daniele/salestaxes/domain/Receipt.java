package com.daniele.salestaxes.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Receipt {

    private List<Entry> entryList = new ArrayList<>();
    private BigDecimal totalCartPrice;
    private BigDecimal totalCartTaxes;

    public Receipt(List<Entry> entryList) {

        this.entryList = entryList;
        totalCartPrice = new BigDecimal("0");
        totalCartTaxes = new BigDecimal("0");
        for (Entry entry: entryList) {
            BigDecimal quantity = new BigDecimal(entry.getItem().getQuantity().toString());
            totalCartPrice = totalCartPrice.add(entry.getTotalItemPrice());
            totalCartTaxes = totalCartTaxes.add(entry.getTotalItemTaxes().multiply(quantity));
        }
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        entryList.forEach(entry -> sb.append(entry.getItem()+ ": " + entry.getTotalItemPrice() + "\n\n"));
        sb.append("Sales Taxes: " + totalCartTaxes.toString() + "\n");
        sb.append("Total: " + totalCartPrice.toString());
        return sb.toString();
    }
}

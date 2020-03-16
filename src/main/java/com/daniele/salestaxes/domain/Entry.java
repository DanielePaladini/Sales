package com.daniele.salestaxes.domain;

import com.daniele.salestaxes.domain.goods.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter @Getter
public class Entry {

    private Item item;
    private BigDecimal totalItemPrice;
    private BigDecimal totalItemTaxes;

    @Override
    public String toString() {
        return ""+ item + " " + totalItemPrice.toString() + " " + totalItemTaxes.toString();
    }
}

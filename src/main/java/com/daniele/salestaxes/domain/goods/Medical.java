package com.daniele.salestaxes.domain.goods;

import java.math.BigDecimal;

public class Medical extends Exempted {

    public Medical(String id, String name, String description, Integer quantity, Boolean isImported, BigDecimal price) {
        super(id, name, description, quantity, isImported, price);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getId() { return super.getId(); }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public Integer getQuantity() {
        return super.getQuantity();
    }

    @Override
    public Boolean getIsImported() {
        return super.getIsImported();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }
}

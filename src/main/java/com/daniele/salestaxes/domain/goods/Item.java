package com.daniele.salestaxes.domain.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor @Getter @Setter
public class Item implements Cloneable {

    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private Boolean isImported;
    private BigDecimal price;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(quantity + "  ");
        if (isImported)
            sb.append("imported ");
        sb.append(description);
        return sb.toString();
    }
}

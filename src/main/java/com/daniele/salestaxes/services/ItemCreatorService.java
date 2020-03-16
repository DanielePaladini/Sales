package com.daniele.salestaxes.services;

import com.daniele.salestaxes.domain.goods.Book;
import com.daniele.salestaxes.domain.goods.Food;
import com.daniele.salestaxes.domain.goods.Item;
import com.daniele.salestaxes.domain.goods.Medical;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemCreatorService {

    List<Item> itemList = new ArrayList<>();

    public ItemCreatorService() {

        Book book1 = new Book(UUID.randomUUID().toString(),"Game of Thrones", "Book", 0, false, new BigDecimal("12.49"));
        Item cd1 = new Item(UUID.randomUUID().toString(),"The Dark Side Of The Moon", "Music CD", 0, false, new BigDecimal("14.99"));
        Food chocolate1 = new Food(UUID.randomUUID().toString(),"Venchi", "Chocolate", 0, false, new BigDecimal("0.85"));

        Food chocolate2 = new Food(UUID.randomUUID().toString(), "Caffarel", "Chocolate", 0, true, new BigDecimal("10.00"));
        Item perfume1 = new Item(UUID.randomUUID().toString(), "Versace", "Bottle of Perfume", 0, true, new BigDecimal("47.50"));

        Item perfume2 = new Item(UUID.randomUUID().toString(), "Chanel", "Bottle of Perfume", 0 , true, new BigDecimal("27.99"));
        Item perfume3 = new Item(UUID.randomUUID().toString(), "Armani", "Bottle of Perfume", 0, false, new BigDecimal("18.99"));
        Medical medical1 = new Medical(UUID.randomUUID().toString(), "Moment", "Headache pills", 0, false, new BigDecimal("9.75"));
        Food chocolate3 = new Food(UUID.randomUUID().toString(), "Lindt", "Chocolate", 0, true, new BigDecimal("11.25"));
        itemList.add(book1);
        itemList.add(cd1);
        itemList.add(chocolate1);
        itemList.add(chocolate2);
        itemList.add(perfume1);
        itemList.add(perfume2);
        itemList.add(perfume3);
        itemList.add(medical1);
        itemList.add(chocolate3);
    }

    public List<Item> getItemList(){
        return itemList;
    }
}

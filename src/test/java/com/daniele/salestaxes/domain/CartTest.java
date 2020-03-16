package com.daniele.salestaxes.domain;

import com.daniele.salestaxes.domain.goods.Book;
import com.daniele.salestaxes.domain.goods.Food;
import com.daniele.salestaxes.domain.goods.Item;
import com.daniele.salestaxes.domain.goods.Medical;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CartTest {

    Book book1 = new Book(UUID.randomUUID().toString(),"Game of Thrones", "Book", 2, false, new BigDecimal("12.49"));
    Item cd1 = new Item(UUID.randomUUID().toString(),"The Dark Side Of The Moon", "Music CD", 1, false, new BigDecimal("14.99"));
    Food chocolate1 = new Food(UUID.randomUUID().toString(),"Venchi", "Chocolate", 1, false, new BigDecimal("0.85"));

    Food chocolate2 = new Food(UUID.randomUUID().toString(), "Caffarel", "Chocolate", 1, true, new BigDecimal("10.00"));
    Item perfume1 = new Item(UUID.randomUUID().toString(), "Versace", "Bottle of Perfume", 1, true, new BigDecimal("47.50"));

    Item perfume2 = new Item(UUID.randomUUID().toString(), "Chanel", "Bottle of Perfume", 1 , true, new BigDecimal("27.99"));
    Item perfume3 = new Item(UUID.randomUUID().toString(), "Armani", "Bottle of Perfume", 1, false, new BigDecimal("18.99"));
    Medical medical1 = new Medical(UUID.randomUUID().toString(), "Moment", "Packet of headache", 1, false, new BigDecimal("9.75"));
    Food chocolate3 = new Food(UUID.randomUUID().toString(), "Lindt", "Chocolate", 3, true, new BigDecimal("11.25"));

    @Autowired
    Cart shoppingCart;
    Receipt receipt;

    @Test
    public void testInput1(){
        shoppingCart.discardAll();
        shoppingCart.addItem(book1);
        shoppingCart.addItem(cd1);
        shoppingCart.addItem(chocolate1);
        receipt = shoppingCart.checkOut();
        BigDecimal totalCartPrice = receipt.getTotalCartPrice();
        BigDecimal totalCartTaxes = receipt.getTotalCartTaxes();

        assertEquals("42.32", totalCartPrice.toString());
        assertEquals("1.50", totalCartTaxes.toString());
    }

    @Test
    public void testInput2(){
        shoppingCart.discardAll();
        shoppingCart.addItem(chocolate2);
        shoppingCart.addItem(perfume1);
        receipt = shoppingCart.checkOut();
        BigDecimal totalCartPrice = receipt.getTotalCartPrice();
        BigDecimal totalCartTaxes = receipt.getTotalCartTaxes();

        assertEquals("65.15", totalCartPrice.toString());
        assertEquals("7.65", totalCartTaxes.toString());
    }

    @Test
    public void testInput3(){
        shoppingCart.discardAll();
        shoppingCart.addItem(perfume2);
        shoppingCart.addItem(perfume3);
        shoppingCart.addItem(medical1);
        shoppingCart.addItem(chocolate3);
        receipt = shoppingCart.checkOut();
        BigDecimal totalCartPrice = receipt.getTotalCartPrice();
        BigDecimal totalCartTaxes = receipt.getTotalCartTaxes();

        assertEquals("98.38", totalCartPrice.toString());
        assertEquals("7.90", totalCartTaxes.toString());
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {
    Checkout co;

    @BeforeEach
    public void setUp() {
        co = new Checkout();
    }

    // add test for retriveing an item that doesn't exist

    @Test
    void addOneItem() {
        Item item = new Item("009", "Item 1", 9.99);
       co.addItem(item);

        HashMap<String, Item> items = co.getItems();

        assertEquals(items.size(),1);
        assertEquals(items.get("009").getId(),"009");
        assertEquals(items.get("009").getName(),"Item 1");
        assertEquals(items.get("009").getPrice(),9.99);
    }

    @Test
    void addAllItems() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.99));
        items.put("011", new Item("011", "Item 3", 99999.99));


        assertEquals(items.size(),2);
        assertEquals(items.get("009").getId(),"009");
        assertEquals(items.get("009").getName(),"Item 1");
        assertEquals(items.get("009").getPrice(),9.99);

        assertEquals(items.get("011").getId(),"011");
        assertEquals(items.get("011").getName(),"Item 3");
        assertEquals(items.get("011").getPrice(),99999.99);
    }

    @Test
    void getPrice() {
        // add test to get price for items  that don't exist as well
        HashMap<String, Item> items = new HashMap<String, Item>();
        Checkout co = new Checkout();
        items.put("009", new Item("009", "Item 1", 9.99));
        assertEquals(items.get("009").getPrice(),9.99);
    }

//    @Test
//    void getPriceForItemDoesNotExist() {
//        HashMap<String, Item> items = new HashMap<String, Item>();
//        Checkout co = new Checkout();
//        items.put("009", new Item("009", "Item 1", 9.99));
//        assertEquals(items.get("001").getPrice(),0);
//    }

    @Test
    void scan() {
        // test with one item that exits  and brand new item

        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.99));

        HashMap<String, BasketItem> basketItems = new HashMap<String, BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),1);
    }

    @Test
    void totalForOneItem() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.99));

        HashMap<String, BasketItem> basketItems = new HashMap<String, BasketItem>();

        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        assertEquals(checkout.total(), "9.99");
    }

    @Test
    void totalForMultiUniqueItemsNotEligiableForDiscountOnTotal() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.00));
        items.put("008", new Item("008", "Item 2", 10.99));


        HashMap<String, BasketItem> basketItems = new HashMap<String, BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        basketItems =  checkout.scan("008", items);
        assertEquals(checkout.total(), "19.99");
    }


    @Test
    void totalForMultiUniqueItemsEligableDiscountTotal() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 20));
        items.put("008", new Item("008", "Item 2", 50));


        HashMap<String, BasketItem> basketItems = new HashMap<String, BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        basketItems =  checkout.scan("008", items);
        assertEquals(checkout.total(), "63");
    }
}
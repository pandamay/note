package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void addAllItems() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        Checkout co = new Checkout();
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
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.99));

        LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        assertEquals(basketItems.getFirst().getPrice(),9.99);
        assertEquals(basketItems.getFirst().getId(),"009");
    }

    @Test
    void totalForOneItem() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.99));

        LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        assertEquals(checkout.total(), "9.99");
    }

    @Test
    void totalForMultiUniqueItemsNotEligiableForDiscountOnTotal() {
        HashMap<String, Item> items = new HashMap<String, Item>();
        items.put("009", new Item("009", "Item 1", 9.00));
        items.put("008", new Item("008", "Item 2", 10.99));


        LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();
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


        LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();
        Checkout checkout = new Checkout();
        basketItems =  checkout.scan("009", items);
        basketItems =  checkout.scan("008", items);
        assertEquals(checkout.total(), "63");
    }
}
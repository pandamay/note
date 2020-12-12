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
    void addItemTestAddOneItem() {
        Item item = new Item("009", "Item 1", 9.99);
       co.addItem(item);

        HashMap<String, Item> items = co.getItems();

        assertEquals(items.size(),1);
        assertEquals(items.get("009").getId(),"009");
        assertEquals(items.get("009").getName(),"Item 1");
        assertEquals(items.get("009").getPrice(),9.99);
    }

    @Test
    void addItemTestAddTwoItem() {
        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 23.45);
        co.addItem(item);
        co.addItem(item2);

        HashMap<String, Item> items = co.getItems();

        assertEquals(items.size(),2);
        assertEquals(items.get("009").getId(),"009");
        assertEquals(items.get("009").getName(),"Item 1");
        assertEquals(items.get("009").getPrice(),9.99);

        assertEquals(items.get("010").getId(),"010");
        assertEquals(items.get("010").getName(),"Item 2");
        assertEquals(items.get("010").getPrice(),23.45);
    }

    @Test
    void addAllItemsTest() {
        HashMap<String, Item> items = co.addAllItems();

        assertEquals(items.size(),3);
        assertEquals(items.get("001").getId(),"001");
        assertEquals(items.get("001").getName(),"Travel Card Holder");
        assertEquals(items.get("001").getPrice(),9.25);

        assertEquals(items.get("002").getId(),"002");
        assertEquals(items.get("002").getName(),"Personalised cufflinks");
        assertEquals(items.get("002").getPrice(),45.00);

        assertEquals(items.get("003").getId(),"003");
        assertEquals(items.get("003").getName(),"Kids T-shirt");
        assertEquals(items.get("003").getPrice(),19.95);
    }


    @Test
    void getItemPriceTestForItemThatExists() {
        Item item = new Item("009", "Item 1", 9.99);
        HashMap<String, Item> items = co.addItem(item);
        assertEquals(co.getItemPrice("009", items),9.99);
    }

    @Test
    void getItemPriceTestForItemDoesNotExist() {
        Item item = new Item("009", "Item 1", 9.99);
        HashMap<String, Item> items = co.addItem(item);
        assertEquals(co.getItemPrice("077", items),0);
    }



    @Test
    void scanTestForOneItem() {
        // TODO add test with one item that exits  and brand new item

        Item item = new Item("009", "Item 1", 9.99);
        HashMap<String, Item> items = co.addItem(item);
        co.scan("009", items);

        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;
        assertEquals(basketItems.size(), 1);
        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),1);
    }

    @Test
    void scanTestsForTwoUniqueItems() {
        // TODO add test with one item that exits  and brand new item

        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 17.03);
        co.addItem(item);
        co.addItem(item2);

        HashMap<String, Item> items = co.getItems();
        co.scan("009", items);
        co.scan("010", items);


        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        assertEquals(basketItems.size(), 2);

        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),1);


        assertEquals(basketItems.get("010").getPrice(),17.03);
        assertEquals(basketItems.get("010").getQuantity(),1);
    }

    @Test
    void scanTestsForSameTwoItems() {
        // TODO add test with one item that exits  and brand new item

        Item item = new Item("009", "Item 1", 9.99);
        co.addItem(item);

        HashMap<String, Item> items = co.getItems();
        co.scan("009", items);
        co.scan("009", items);


        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        assertEquals(basketItems.size(), 1);

        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),2);
    }

    @Test
    void scanTestsForOneUniqueItemAndSameTwoItems() {
        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 17.03);
        co.addItem(item);
        co.addItem(item2);

        HashMap<String, Item> items = co.getItems();
        co.scan("009", items);
        co.scan("010",items);
        co.scan("009", items);


        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        assertEquals(basketItems.size(), 2);

        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),2);


        assertEquals(basketItems.get("010").getPrice(),17.03);
        assertEquals(basketItems.get("010").getQuantity(),1);
    }

    @Test
    void getTotalPriceForEachItemNumberTestWithOneItem(){
        Item item = new Item("009", "Item 1", 9.99);
        HashMap<String, Item> items = co.addItem(item);
        // TODO needs to be done after scan test
//        co.getTotalPriceForEachItemNumber();
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
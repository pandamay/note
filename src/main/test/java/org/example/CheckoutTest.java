package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {
    Checkout co;

    @BeforeEach
    public void setUp() {
        co = new Checkout();
    }

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
    void formatPriceTestsWith2DecimalsRoundingUp() {
        double newPrice = co.formatPrice(9999.235124);
        assertEquals(newPrice, 9999.24);
    }

    @Test
    void getTotalPriceForEachItemNumberTestWith2Items() {
        Item item = new Item("009", "Item 1", 5.06);
        co.addItem(item);

        HashMap<String, Item> items = co.getItems();
        co.scan("009");
        co.scan("009");
        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        double price = co.getTotalPriceForEachItemNumber(basketItems.get("009"));
        assertEquals(price,10.12);
    }

    @Test
    void getTotalPriceForEachItemNumberTestWith1Item() {
        Item item = new Item("009", "Item 1", 5.06);
        co.addItem(item);

        HashMap<String, Item> items = co.getItems();
        co.scan("009");
        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        double price = co.getTotalPriceForEachItemNumber(basketItems.get("009"));
        assertEquals(price,5.06);
    }

    @Test
    void scanTestForOneItem() {
        Item item = new Item("009", "Item 1", 9.99);
        HashMap<String, Item> items = co.addItem(item);
        co.scan("009");

        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;
        assertEquals(basketItems.size(), 1);
        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),1);
    }

    @Test
    void scanTestsForTwoUniqueItems() {
        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 17.03);
        co.addItem(item);
        co.addItem(item2);

        HashMap<String, Item> items = co.getItems();
        co.scan("009");
        co.scan("010");
        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        assertEquals(basketItems.size(), 2);

        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),1);

        assertEquals(basketItems.get("010").getPrice(),17.03);
        assertEquals(basketItems.get("010").getQuantity(),1);
    }

    @Test
    void scanTestsForSameTwoItems() {
        Item item = new Item("009", "Item 1", 9.99);
        co.addItem(item);

        HashMap<String, Item> items = co.getItems();
        co.scan("009");
        co.scan("009");
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
        co.scan("009");
        co.scan("010");
        co.scan("009");

        HashMap<String, BasketItem> basketItems = co.getBasketItems() ;

        assertEquals(basketItems.size(), 2);

        assertEquals(basketItems.get("009").getPrice(),9.99);
        assertEquals(basketItems.get("009").getQuantity(),2);

        assertEquals(basketItems.get("010").getPrice(),17.03);
        assertEquals(basketItems.get("010").getQuantity(),1);
    }


    @Test
    void totalTestForOneItem() {
        Item item = new Item("009", "Item 1", 9.99);
        co.addItem(item);

        co.scan("009");

        assertEquals(co.total(), 9.99);
    }

    @Test
    void totalTestFortwoUniqueItems(){
        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 17.03);

        co.addItem(item);
        co.addItem(item2);

        co.scan("009");
        co.scan("010");

        assertEquals(co.total(), 27.03);
    }

    @Test
    void totalTestFortwoOfSameItems(){
        Item item = new Item("009", "Item 1", 9.99);

        co.addItem(item);

        co.scan("009");
        co.scan("009");

        assertEquals(co.total(), 19.98);
    }

    @Test
    void totalTestsForOneUniqueItemAndSameTwoItems(){
        Item item = new Item("009", "Item 1", 9.99);
        Item item2 = new Item("010", "Item 2", 17.03);

        co.addItem(item);
        co.addItem(item2);

        co.scan("009");
        co.scan("010");
        co.scan("009");

        assertEquals(co.total(), 37.02);
    }

    @Test
    void totalForMultiUniqueItemsNotEligiableForDiscountOnTotal() {
        Item item1 = new Item("008", "Item 1", 20.00);
        Item item2 = new Item("009", "Item 2", 10.00);

        co.addItem(item1);
        co.addItem(item2);

        co.scan("009");
        co.scan("008");
        assertEquals(co.total(), 30.00);
    }


    @Test
    void totalForMultiUniqueItemsEligableDiscountTotal() {
        Item item1 = new Item("008", "Item 1", 50.00);
        Item item2 = new Item("009", "Item 2", 20.00);

        co.addItem(item1);
        co.addItem(item2);

        co.scan("009");
        co.scan("008");
        assertEquals(co.total(), 63.00);
    }

    @Test
    void totalTestForTwoOfSameItemsEligibleForDiscount(){
        Item item = new Item("001", "Item 1", 9.99);
        co.addItem(item);

        co.scan("001");
        co.scan("001");

        assertEquals(co.total(), 17.00);
    }

    @Test
    void totalTestForBasketEligibleForTwoItemDiscountAndTotalDiscount(){
        Item item = new Item("001", "Item 1", 9.99);
        Item item2 = new Item("008", "Item 1", 60.00);
        Item item3 = new Item("009", "Item 1", 20.00);

        co.addItem(item);
        co.addItem(item2);
        co.addItem(item3);

        co.scan("001");
        co.scan("008");
        co.scan("009");
        co.scan("001");

        assertEquals(co.total(), 87.3);
    }
}
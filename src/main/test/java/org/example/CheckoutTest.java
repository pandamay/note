package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void addAllItems() {
        HashMap<Integer, Item> items = new HashMap<Integer, Item>();

        Checkout co = new Checkout();
        co.addAllItems();

        assertEquals(items.size(),3);
        assertEquals(items.get(001),"55555");
    }

    @Test
    void getPrice() {

    }

    @Test
    void scan() {
    }

    @Test
    void total() {
    }
}
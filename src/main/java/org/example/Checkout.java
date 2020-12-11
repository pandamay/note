package org.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Checkout  {

    //TODO, add access modifiers to classes and variables and fix private and public

    HashMap<String, Item> items;
    HashMap<String, BasketItem> basketItems;

    public Checkout() {
        items = new HashMap<String, Item>();
        basketItems = new HashMap<String, BasketItem>();
    }

    public  HashMap<String, Item> addItem(Item item)  {
        items.put(item.getId(), item);
        return items;
    }

    public HashMap<String, Item> addAllItems() {
        items.put("001", new Item("001", "Travel Card Holder", 9.25));
        items.put("002", new Item("002", "Personalised cufflinks", 45.00));
        items.put("003", new Item("003", "Kids T-shirt", 19.95));
        return items;
    }

    public double getPrice(String itemNumber, HashMap<String, Item>  items) {
        if (items.containsKey(itemNumber)) {
            Item item = items.get(itemNumber);
            return item.getPrice();
        }
        return 0;
    }

    public HashMap<String, BasketItem> scan(String itemNumber, HashMap<String, Item> items) {
        double price = getPrice(itemNumber, items);
        if (basketItems.containsKey(itemNumber) ) {
            int quantity = basketItems.get(itemNumber).getQuantity();
            basketItems.put(itemNumber, new BasketItem(price, quantity+1));
        }
        else {
            basketItems.put(itemNumber, new BasketItem(price));
        }
        return  basketItems;
    }

    private double applyDiscountOnTotalIfApplies(double totalPrice){
        if (totalPrice > 60) {
            return (totalPrice - (totalPrice*0.1));
        }
        return totalPrice;
    }

    private HashMap<String, BasketItem> applyMultiProductDeals() {
        return basketItems;
    }

    public String total() {

        double totalPrice = 0;


        for (Map.Entry<String, BasketItem> entry : basketItems.entrySet()) {
            String key = entry.getKey();
            BasketItem basketItem = entry.getValue();
            totalPrice = totalPrice + basketItem.getPrice();
        }

        DecimalFormat df = new DecimalFormat("#############.###");
        return (df.format(applyDiscountOnTotalIfApplies(totalPrice)));

    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public HashMap<String, BasketItem> getBasketItems() {
        return basketItems;
    }
}

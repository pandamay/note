package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Checkout {
    HashMap<Integer, Item> items = new HashMap<Integer, Item>();
    LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();

    public void addAllItems() {
        items.put(001, new Item(001, "Travel Card Holder", 9.25));
        items.put(002, new Item(002, "Personalised cufflinks", 45.00));
        items.put(003, new Item(003, "Kids T-shirt", 19.95));
    }

    public double getPrice(int itemNumber) {
        if (items.containsKey(itemNumber)) {
            Item item = items.get(itemNumber);
            return item.getPrice();
        }
        return 0;
    }

    public void scan(int itemNumber) {
        double price = getPrice(itemNumber);
        basketItems.add(new BasketItem(itemNumber, price));

    }

    private double applyDiscountOnTotalIfApplies(double totalPrice){
        if (totalPrice > 60) {
            return (totalPrice - (totalPrice*0.1));
        }
        return totalPrice;
    }

    private LinkedList<BasketItem> applyMultiProductDeals() {
//        basketItems.stream().map(b -> b.getId().equals(001)

        for (BasketItem b: basketItems)  {

        }


        return basketItems;

    }

    public double total() {

        double totalPrice = 0;

        LinkedList<BasketItem> basketItems = applyMultiProductDeals();

        Iterator basketIt = basketItems.iterator();
        while(basketIt.hasNext()) {
            BasketItem a  = (BasketItem)basketIt.next();
            totalPrice = totalPrice + a.getPrice();
        }
        return applyDiscountOnTotalIfApplies(totalPrice);

    }
}

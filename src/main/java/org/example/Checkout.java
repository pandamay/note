package org.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Checkout extends Base {


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

    public LinkedList<BasketItem> scan(String itemNumber,  HashMap<String, Item>  items) {
        double price = getPrice(itemNumber, items);
        basketItems.add(new BasketItem(itemNumber, price));
        return  basketItems;

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

    public String total() {

        double totalPrice = 0;

        LinkedList<BasketItem> basketItems = applyMultiProductDeals();

        Iterator basketIt = basketItems.iterator();
        while(basketIt.hasNext()) {
            BasketItem a  = (BasketItem)basketIt.next();
            totalPrice = totalPrice + a.getPrice();
        }
        DecimalFormat df = new DecimalFormat("#############.###");
        return (df.format(applyDiscountOnTotalIfApplies(totalPrice)));

    }
}

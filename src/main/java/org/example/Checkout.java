package org.example;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Checkout  {

    HashMap<String, Item> items;
    HashMap<String, BasketItem> basketItems;
    Rules promotionalRules;
    private static DecimalFormat df = new DecimalFormat("0.00");


    public Checkout() {
        items = new HashMap<String, Item>();
        basketItems = new HashMap<String, BasketItem>();
        this.promotionalRules =  new Rules();
    }

    public Checkout(Rules promotionalRules) {
        items = new HashMap<String, Item>();
        basketItems = new HashMap<String, BasketItem>();
        this.promotionalRules =  new Rules(promotionalRules.multiItemDiscountRule, promotionalRules.totalCostRule);
        addAllItems();

    }

    public  HashMap<String, Item> addItem(Item item)  {
        items = getItems();
        items.put(item.getId(), item);
        return items;
    }

    public HashMap<String, Item> addAllItems() {
        items.put("001", new Item("001", "Travel Card Holder", 9.25));
        items.put("002", new Item("002", "Personalised cufflinks", 45.00));
        items.put("003", new Item("003", "Kids T-shirt", 19.95));
        return items;
    }

    protected double getItemPrice(String itemNumber, HashMap<String, Item> items) {
        if (items.containsKey(itemNumber)) {
            Item item = items.get(itemNumber);
            return item.getPrice();
        }
        return 0;
    }

    protected double getTotalPriceForEachItemNumber(BasketItem basketItem){
        double totalPriceForItem = 0;
        double pricePerItem = basketItem.getPrice();
        int quantityPerItem = basketItem.getQuantity();
        totalPriceForItem = pricePerItem * quantityPerItem;
        return totalPriceForItem;
    }

    protected Double formatPrice(double priceAfterRules) {
        df.setRoundingMode(RoundingMode.UP);
        String formatPrice = df.format(priceAfterRules);
        return Double.valueOf(formatPrice);
    }

    public HashMap<String, BasketItem> scan(String itemNumber) {
        items = getItems();
        double price = getItemPrice(itemNumber, items);
        if (basketItems.containsKey(itemNumber) ) {
            int quantity = basketItems.get(itemNumber).getQuantity();
            double newPrice = promotionalRules.getMultiItemDiscountRule().applyDiscountRule(itemNumber,quantity,price);
            basketItems.put(itemNumber, new BasketItem(newPrice, quantity+1));
        }
        else {
            basketItems.put(itemNumber, new BasketItem(price));
        }
        return  basketItems;
    }

    public Double total() {
        double totalPrice = 0;
        for (Map.Entry<String, BasketItem> entry : basketItems.entrySet()) {
            String key = entry.getKey();
            BasketItem basketItem = entry.getValue();
            double totalPriceForItem = getTotalPriceForEachItemNumber(basketItem);
            totalPrice = totalPrice + totalPriceForItem;
        }
        double priceAfterRules = promotionalRules.getTotalCostRule().applyDiscountRule(totalPrice);
        Double formatedPrice = formatPrice(priceAfterRules);

        return (formatedPrice);

    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public HashMap<String, BasketItem> getBasketItems() {
        return basketItems;
    }
}

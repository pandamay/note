package org.example;
import java.math.BigDecimal;

public class BasketItem {

    private String id;
    private double price;

    public BasketItem(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}

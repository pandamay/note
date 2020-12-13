package org.example;
import java.math.BigDecimal;

public class BasketItem {

    private int quantity = 0;
    private double price;

    public BasketItem (double price) {
        this.quantity = 1;
        this.price = price;
    }

    public BasketItem (double price, int quantity) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}

package org.example;
import java.math.BigDecimal;

public class BasketItem {

    // todo.. only if time change to big decimal

    private int quantity = 0;
    private double price;

    public BasketItem (double price) {
        this.quantity = this.quantity + 1;
        this.price = price;
    }

    public BasketItem (double price, int quantity) {
        this.quantity = this.quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}

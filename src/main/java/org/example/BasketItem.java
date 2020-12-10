package org.example;

public class BasketItem {

    private int id;
    private double price;

    public BasketItem(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}

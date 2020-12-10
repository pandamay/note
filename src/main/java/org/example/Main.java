package org.example;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Item> items = new HashMap<String, Item>();


        Checkout checkout = new Checkout();

        items = checkout.addAllItems();
        checkout.scan("001", items);
        checkout.scan("003", items);
        checkout.scan("002", items);
        checkout.scan("002", items);


        System.out.println("Total Price = " + checkout.total());



    }
}

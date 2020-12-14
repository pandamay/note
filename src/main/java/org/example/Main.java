package org.example;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Item> items = new HashMap<String, Item>();
        Rules promotionRules = new Rules();

        Checkout checkout = new Checkout(promotionRules);

        checkout.addItem(new Item("001", "Travel Card Holder", 9.25));
        checkout.addItem(new Item("002", "Personalised cufflinks", 45.00));
        checkout.addItem(new Item("003", "Kids T-shirt", 19.95));

        checkout.scan("001");
        checkout.scan("002");
        checkout.scan("003");

        System.out.println("Total Price Expected = " + checkout.total());



    }
}

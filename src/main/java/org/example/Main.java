package org.example;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Item> items = new HashMap<String, Item>();


        // TODO List <Rules> rules;   // build in here.. or  add class to build it
    //        Checkout checkout = new Checkout(rules);
        Checkout checkout = new Checkout();

        //TODO  add  each item  from  main
        // . Or create another class just to  add  items.  HAve  a static hashmap  there.


        checkout.addItem(new Item("001", "Travel Card Holder", 9.25));
        checkout.addItem(new Item("002", "Personalised cufflinks", 45.00));
        items = checkout.addItem(new Item("003", "Kids T-shirt", 19.95));

//        items = checkout.addAllItems();  //should be removed and replaced with adding each items
        checkout.scan("001", items);
        checkout.scan("003", items);
        checkout.scan("002", items);
//        checkout.scan("002", items);


        System.out.println("Total Price = " + checkout.total());



    }
}

package org.example;

public class Main {

    public static void main(String[] args) {

        Checkout checkout = new Checkout();

        checkout.addAllItems();
        checkout.scan(001);
        checkout.scan(003);
        checkout.scan(002);
        checkout.scan(002);


        System.out.println("Totol Price = " + checkout.total());



    }
}

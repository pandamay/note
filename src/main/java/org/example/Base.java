package org.example;

import java.util.HashMap;
import java.util.LinkedList;

public class Base {

    // how od I do  Global Item and Global basketItems  do  I need base class everyone inherits from
    HashMap<String, Item> items = new HashMap<String, Item>();
    LinkedList<BasketItem> basketItems = new LinkedList<BasketItem>();   //Where should Basket live?? which class
}

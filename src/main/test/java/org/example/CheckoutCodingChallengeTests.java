package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutCodingChallengeTests {

    Rules promotionRules;

    @BeforeEach
    public void setUp() {
        promotionRules = new Rules();
    }

    @Test
    void TestData1Test(){
        Checkout co = new Checkout(promotionRules);
        co.scan("001");
        co.scan("002");
        co.scan("003");

        Double price = co.total();
        assertEquals(price, 66.78);
        System.out.println("Total Price Expected = " + price);
    }

    @Test
    void TestData2Test(){
        Checkout co = new Checkout(promotionRules);
        co.scan("001");
        co.scan("003");
        co.scan("001");

        Double price = co.total();
        assertEquals(price, 36.95);
        System.out.println("Total Price Expected = " + price);

    }

    @Test
    void TestData3Test(){
        Checkout co = new Checkout(promotionRules);
        co.scan("001");
        co.scan("002");
        co.scan("001");
        co.scan("003");

        Double price = co.total();
        assertEquals(price, 73.76);
        System.out.println("Total Price Expected = " + price);

    }
}
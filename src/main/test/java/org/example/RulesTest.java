package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    MultiItemDiscountRule mdr;
    TotalCostRule tcr;

    @BeforeEach
    public void setUp() {
        mdr = new MultiItemDiscountRule();
        tcr = new TotalCostRule();
    }

    @Test
    public void MultiItemDiscountRuleTestWhereRuleApplies(){
        double newPrice = mdr.applyDiscountRule("001",1,9.99);
        assertEquals(newPrice, 8.50);
    }

    @Test
    public void MultiItemDiscountRuleTestsWhereItemMatchesButQuantityDoesNot(){
        double newPrice = mdr.applyDiscountRule("001",0,9.99);
        assertEquals(newPrice, 9.99);
    }

    @Test
    public void MultiItemDiscountRuleTestsWhereQuantityMatchesButItemDoesNot(){
        double newPrice = mdr.applyDiscountRule("002",2,9.99);
        assertEquals(newPrice, 9.99);
    }

    @Test
    public void MultiItemDiscountRuleTestsWhereItemDoesNotExist(){
        double newPrice = mdr.applyDiscountRule("023",2,9.99);
        assertEquals(newPrice, 9.99);
    }

    @Test void TotalCostRuleTestWhereRuleApplies() {
        double newPrice = tcr.applyDiscountRule(100);
        assertEquals(newPrice,90);
    }

    @Test void TotalCostRuleTestWhereRuleDoesNotApply() {
        double newPrice = tcr.applyDiscountRule(50);
        assertEquals(newPrice,50);
    }


}
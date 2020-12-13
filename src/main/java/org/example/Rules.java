package org.example;

public class Rules {
    MultiItemDiscountRule multiItemDiscountRule;
    TotalCostRule totalCostRule;


    public Rules() {
        this.multiItemDiscountRule = new MultiItemDiscountRule();
        this.totalCostRule = new TotalCostRule();
    }
    public Rules(MultiItemDiscountRule multiItemDiscountRule, TotalCostRule totalCostRule) {
        this.multiItemDiscountRule =  new MultiItemDiscountRule();
        this.totalCostRule = new TotalCostRule();
    }

    public MultiItemDiscountRule getMultiItemDiscountRule() {
        return multiItemDiscountRule;
    }

    public TotalCostRule getTotalCostRule() {
        return totalCostRule;
    }
}

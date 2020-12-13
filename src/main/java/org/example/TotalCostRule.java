package org.example;

public class TotalCostRule {
    double totalCostEligibleForDiscount;
    double discountPercentage;

    public TotalCostRule(double totalCostEligibleForDiscount,double discountPercentage) {
        this.totalCostEligibleForDiscount = totalCostEligibleForDiscount;
        this.discountPercentage = discountPercentage;
    }

    public TotalCostRule() {
        this.totalCostEligibleForDiscount = 60;
        this.discountPercentage = 10;
    }

    public double applyDiscountRule(double totalPrice) {
        double percentageToMultiply = discountPercentage / 100;

        if (totalPrice > totalCostEligibleForDiscount) {
            return (totalPrice - (totalPrice*percentageToMultiply));
        }
        return totalPrice;

    }

    public double getTotalCostEligibleForDiscount() {
        return totalCostEligibleForDiscount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}



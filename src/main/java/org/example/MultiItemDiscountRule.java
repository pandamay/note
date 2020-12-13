package org.example;

public class MultiItemDiscountRule {

    String eligibleItemNumber;
    int eligibleQuantity;
    double newPrice;

    public MultiItemDiscountRule(String itemNumber, int quantity, double newPrice) {
        this.eligibleItemNumber = itemNumber;
        this.eligibleQuantity = quantity;
        this.newPrice = newPrice;
    }

    public MultiItemDiscountRule() {
        this.eligibleItemNumber = "001";
        this.eligibleQuantity = 2;
        this.newPrice = 8.50;
    }

    public double applyDiscountRule(String itemNumber, int quantity, double price) {
        if ((itemNumber.equals(eligibleItemNumber)) && quantity >= eligibleQuantity - 1)  {
            return newPrice;
        } else {
            return price;
        }

    }


    public String getEligibleItemNumber() {
        return eligibleItemNumber;
    }

    public int getEligibleQuantity() {
        return eligibleQuantity;
    }

    public double getNewPrice() {
        return newPrice;
    }
}

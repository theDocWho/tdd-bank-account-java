package org.xpdojo.bank;

/**
 * Immutable class to represent Money as a concept.
 * This class should have no accessor methods.
 */
public class Money {
    private final Integer amount;
    private final String currency;

    public Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }
    public int getAmount(){
        return amount;
    }
    public String getCurrency(){
        return currency;
    }
}

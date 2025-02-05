package org.xpdojo.bank;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Account {
    private final BlockingQueue<Money> balance ;

    public Account(int amount, String currency ){
        if(amount<0) throw new RuntimeException("Account cannot be opened with -ve balance");
        balance = new LinkedBlockingQueue<>(1);
        balance.add(new Money(amount,currency));
    }

    public Account(String currency){
        this(0,currency);
    }

    public Account(int amount){
        this(amount,"INR");
    }


    public void deposit(int i) throws RuntimeException{
        Money money = null;
        try {
            money = this.balance.poll(5000, TimeUnit.MILLISECONDS);
            money = new Money(money.getAmount()+i,money.getCurrency() );
            this.balance.put(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Account being used by someone else");
        }
    }

    public void withdrawal(int i)throws RuntimeException{
        try {
            Money money = this.balance.poll(5000, TimeUnit.MILLISECONDS);
            if(money.getAmount()-i>=0){
               money = new Money(money.getAmount()-i,money.getCurrency() );
               this.balance.put(money);
            }else{
                this.balance.put(money);
                throw new RuntimeException("The current balance amount  is lower than withdrawal amount");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Account being used by someone else");
        }
    }

    public String checkBalance() throws RuntimeException{
        return this.balance.peek().getCurrency()+" "+this.balance.peek().getAmount();
    }
}

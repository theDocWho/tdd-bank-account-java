package org.xpdojo.bank;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Account {
    private final BlockingDeque<Money> balance ;

    public Account(int amount, String currency ){
        if(amount<0) throw new RuntimeException("Account cannot be opened with -ve balance");
        balance = new LinkedBlockingDeque<>(1);
        balance.add(new Money(amount,currency));
    }

    public Account(String currency){
        this(0,currency);
    }

    public Account(int amount){
        this(amount,"INR");
    }


    public void deposit(int i) throws RuntimeException{
        Money money = this.balance.poll();
        money = new Money(money.getAmount()+i,money.getCurrency() );
        try {
            this.balance.put(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Account being used by someone else");
        }
    }

    public void withdrawal(int i)throws RuntimeException{
        if(this.balance.peek().getAmount()-i>=0){
            Money money = this.balance.poll();
            money = new Money(money.getAmount()-i,money.getCurrency() );
            try {
                this.balance.put(money);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Account being used by someone else");
            }
        }else{
            throw new RuntimeException("The current balance amount  is lower than withdrawal amount");
        }

    }

    public String checkBalance() throws RuntimeException{
        return this.balance.peek().getCurrency()+" "+this.balance.peek().getAmount();
    }
}

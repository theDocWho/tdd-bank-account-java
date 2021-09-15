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


    public void deposit(int i) throws Exception{
        Money money = this.balance.poll();
        money = new Money(money.getAmount()+i,money.getCurrency() );
        this.balance.put(money);
    }

    public void withdrawal(int i)throws Exception{
        if(this.balance.peek().getAmount()-i>=0){
            Money money = this.balance.poll();
            money = new Money(money.getAmount()-i,money.getCurrency() );
            this.balance.put(money);
        }else{
            throw new RuntimeException("The current balance amount  is lower than withdrawal amount");
        }

    }

    public String checkBalance() throws Exception{
        return this.balance.peek().getCurrency()+" "+this.balance.peek().getAmount();
    }
}

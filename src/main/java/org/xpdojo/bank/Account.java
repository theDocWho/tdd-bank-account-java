package org.xpdojo.bank;

public class Account {
    public Integer balance =0;

    public void deposit(int i){
        this.balance += i;
    }

    public void withdrawal(int i){
        if(balance-i>=0){
            this.balance -= i;
        }else{
            throw new RuntimeException("The current balance amount  is lower than withdrawal amount");
        }

    }
}

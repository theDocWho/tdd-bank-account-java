package org.xpdojo.bank;

public class Account {
    public Integer balance =0;

    public void deposit(int i){
        this.balance += i;
    }
}

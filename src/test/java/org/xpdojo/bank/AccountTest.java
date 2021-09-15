package org.xpdojo.bank;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountTest {


    @Test
    public void accountCreationBalanceIsZero() {
        Account account = new Account();
        assertThat(account.balance,is(0));
    }
    @Test
    public void depositAnAmountToIncreaseTheBalance() {

        //arrange
        Account account = new Account();

        //act
        account.deposit(100);
        //assert (I have money);
        assertThat(account.balance,is(100));
    }

    @Test
    public void checkBalanceAfterMultipleDepositsIsSumOfBalances() {
        Account account = new Account();
        account.deposit(100);
        account.deposit(300);
        assertThat(account.balance,is(400));
    }
}

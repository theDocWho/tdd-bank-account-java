package org.xpdojo.bank;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountTest {


    @Test
    public void accountCreationBalanceIsZero() throws Exception {
        Account account = new Account("USD");
        assertThat(account.checkBalance(),is("USD "+0));
    }
    @Test
    public void depositAnAmountToIncreaseTheBalance() throws Exception {
        Account account = new Account(100);
        assertThat(account.checkBalance(),is("INR "+100));
    }

    @Test
    public void checkBalanceAfterMultipleDepositsIsSumOfBalances() throws Exception {
        Account account = new Account(100,"USD");
        account.deposit(300);
        assertThat(account.checkBalance(),is("USD "+400));
    }

    @Test
    public void withdrawalOfAmountAndCheckBalanceIsReflectingCorrectValue() throws Exception {
        Account account = new Account(100);
        account.withdrawal(50);
        assertThat(account.checkBalance(),is("INR "+50));
    }

    @Test
    public void withdrawalOfAmountMoreThanBalanceThrowsBalanceException() throws Exception {
        Account account = new Account(100);

        boolean exceptionThrown = false;

        try{
            account.withdrawal(200);
        }catch (RuntimeException re){
            assertThat(re.getMessage(),is("The current balance amount  is lower than withdrawal amount"));
            exceptionThrown = true;
        }
        assertThat(exceptionThrown,is(true));
        assertThat(account.checkBalance(),is("INR "+100));


    }
}

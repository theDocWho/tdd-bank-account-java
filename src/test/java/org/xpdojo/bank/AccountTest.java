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

    @Test
    public void withdrawalOfAmountAndCheckBalanceIsReflectingCorrectValue() {
        Account account = new Account();
        //act
        account.deposit(100);
        //assert (I have money);
        account.withdrawal(50);
        assertThat(account.balance,is(50));
    }

    @Test
    public void withdrawalOfAmountMoreThanBalanceThrowsBalanceException() throws Exception {
        Account account = new Account();
        account.deposit(100);

        boolean exceptionThrown = false;

        try{
            account.withdrawal(200);
        }catch (RuntimeException re){
            assertThat(re.getMessage(),is("The current balance amount  is lower than withdrawal amount"));
            exceptionThrown = true;
        }
        assertThat(exceptionThrown,is(true));


    }
}

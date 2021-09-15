package org.xpdojo.bank;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;


import java.util.concurrent.CyclicBarrier;

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

    @Test
    public void withdrawalAtSameTimeAndBalanceIsCorrectlyReflected() throws Exception {
        final Account account = new Account(100);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->assertThat(account.checkBalance(),is("INR "+100)));
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.withdrawal(20);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.withdrawal(30);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Thread t1= new Thread(r1);
        t1.start();
        Thread t2= new Thread(r2);
        t2.start();
        t1.join();
        t2.join();
        assertThat(account.checkBalance(),is("INR "+50));

    }

    @Test
    public void depositAtSameTimeAndBalanceIsCorrectlyReflected() throws Exception {
        final Account account = new Account(100);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->assertThat(account.checkBalance(),is("INR "+100)));
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.deposit(20);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.deposit(30);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Thread t1= new Thread(r1);
        t1.start();
        Thread t2= new Thread(r2);
        t2.start();
        t1.join();
        t2.join();
        assertThat(account.checkBalance(),is("INR "+150));

    }

    @Test
    public void depositAndWithdrawalAtSameTimeAndBalanceIsCorrectlyReflected() throws Exception {
        final Account account = new Account(40);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->assertThat(account.checkBalance(),is("INR "+40)));
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.deposit(20);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                    account.withdrawal(30);
                }catch (Exception e){
                    System.out.println("failed to execute");
                }
            }
        };
        Thread t1= new Thread(r1);
        t1.start();
        Thread t2= new Thread(r2);
        t2.start();
        t1.join();
        t2.join();
        assertThat(account.checkBalance(),is("INR "+30));

    }
}

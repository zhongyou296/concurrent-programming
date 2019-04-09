package com.zhongyou.programing.lock.synchronous;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 验证synchronous使用</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-02 23:31</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class SynchronousAccess {

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Thread company = new Thread(new Company(account));
        company.start();

        Thread bank = new Thread(new Bank(account));
        bank.start();

        try {
            company.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            bank.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("balance:" + account.getBalance());
    }
}

class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void addAcount(double account) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += account;
        this.balance = tmp;
    }

    public synchronized void subtractAccount(double account) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= account;
        this.balance = tmp;
    }
}

class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            account.subtractAccount(100);
        }
    }
}

class Company implements Runnable {

    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            account.addAcount(100);
        }
    }
}

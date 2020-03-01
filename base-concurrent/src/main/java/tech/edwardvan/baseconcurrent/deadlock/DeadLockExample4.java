package tech.edwardvan.baseconcurrent.deadlock;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 解决死锁-破坏循环等待条件
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample4 {

    /**
     * 账户数目
     */
    private static final int NUM_ACCOUNTS = 500;
    /**
     * 账户初始金额
     */
    private static final int NUM_MONEY = 1000;
    /**
     * 单线程转账次数
     */
    private static final int NUM_ITERATIONS = 100000;
    /**
     * 转账并发线程数
     */
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        //初始化账户及转账金额
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, NUM_MONEY);
        }
        //创建转账线程
        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }

            }).start();
        }
    }

    /**
     * 转账操作
     */
    public static void transferMoney(Account from, Account to, int amount) {
        class Helper {
            public void transfer() {
                if (from.balance - amount < 0) {
                    log.info("账户({})余额不足,转账失败.", from.id);
                } else {
                    from.balance -= amount;
                    to.balance += amount;
                    log.info("账户({})向账户({})成功转账{}元", from.id, to.id, amount);
                }
            }
        }
        if (from.id < to.id) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        }
    }

    /**
     * 账户
     */
    @AllArgsConstructor
    static class Account {

        /**
         * 账户id
         */
        int id;

        /**
         * 余额
         */
        int balance;
    }

}

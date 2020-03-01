package tech.edwardvan.baseconcurrent.deadlock;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * 用ThreadMXBean检测死锁
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample3 {

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

    public static void main(String[] args) throws InterruptedException {
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

        Thread.sleep(5000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                log.info("发现死锁,线程名称为:{}", threadInfo.getThreadName());
            }
        }

    }

    /**
     * 转账操作
     */
    public static void transferMoney(Account from, Account to, int amount) {
        synchronized (from) {
            synchronized (to) {
                if (from.balance - amount < 0) {
                    log.info("账户({})余额不足,转账失败.", from.id);
                } else {
                    from.balance -= amount;
                    to.balance += amount;
                    log.info("账户({})向账户({})成功转账{}元", from.id, to.id, amount);
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

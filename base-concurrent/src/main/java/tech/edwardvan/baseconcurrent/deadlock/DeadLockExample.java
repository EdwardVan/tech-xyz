package tech.edwardvan.baseconcurrent.deadlock;


import lombok.extern.slf4j.Slf4j;

/**
 * 死锁示例
 * <p>
 * 必要条件
 * 1.互斥条件:一个资源每次只能被一个进程使用,即在一段时间内某 资源仅为一个进程所占有.此时若有其他进程请求该资源,则请求进程只能等待
 * 2.请求与保持条件:进程已经保持了至少一个资源,但又提出了新的资源请求,而该资源已被其他进程占有,此时请求进程被阻塞,但对自己已获得的资源保持不放
 * 3.不可剥夺条件:进程所获得的资源在未使用完毕之前,不能被其他进程强行夺走,即只能由获得该资源的进程自己来释放(只能是主动释放)
 * 4.循环等待条件:若干进程间形成首尾相接循环等待资源的关系
 * <p>
 * 避免死锁:
 * 1.设置超时时间
 * 2.多使用并发类而不是自己设计锁
 * 3.尽量降低锁的使用粒度,用不同的锁而不是一个锁,专锁专用
 * 4.如果能使用同步代码块,就不使用同步方法,自己指定锁对象
 * 5.给你的线程起个有意义的名字,debug和排查时事半功倍
 * 6.避免锁的嵌套
 *
 * @author EdwardVan
 */
@Slf4j
public class DeadLockExample {

    private static Object o1 = new Object();

    private static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o1) {
                log.info("hello 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("hello 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2) {
                log.info("hello 3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("hello 4");
                }
            }
        }).start();
    }

}

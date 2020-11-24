package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写3个线程，ID分别为A、B、C
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出结果为ABCABCABC……
 * 使用技术
 * Lock
 * Condition
 **/
public class Test6ABC {

    public static void main(String[] args) {
        Loop loop = new Loop();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    loop.loopA();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    loop.loopB();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    loop.loopC();
                }
            }
        }, "C").start();
    }
}

class Loop {
    private int num = 1;//当前正在执行线程的标记

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void loopA() {
        lock.lock();
        try {
            if (num != 1) {
                conditionA.await();
            }
            System.out.print(Thread.currentThread().getName());
            num = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();
        try {
            if (num != 2) {
                conditionB.await();
            }
            System.out.print(Thread.currentThread().getName());
            num = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        lock.lock();
        try {
            if (num != 3) {
                conditionC.await();
            }
            System.out.print(Thread.currentThread().getName());
            num = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
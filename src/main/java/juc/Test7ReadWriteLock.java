package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fei.ling
 * @desc 读写锁
 * 写写、写读互斥
 * 读读不互斥
 * @create 2020-11-23 21:38
 **/
public class Test7ReadWriteLock {


    public static void main(String[] args) {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLockDemo.set((int) (Math.random() * 100));
            }
        }, "Write").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.get();
                }
            }, "read" + i).start();
        }


    }
}

class ReadWriteLockDemo {
    private int num = 0;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void get() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public void set(int i) {
        readWriteLock.writeLock().lock();
        try {
            this.num = i;
            System.out.println(Thread.currentThread().getName() + " : " + num);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

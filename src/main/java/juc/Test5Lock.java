package juc;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test5Lock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "t1").start();
        new Thread(ticket, "t2").start();
        new Thread(ticket, "t3").start();
    }
}


class Ticket implements Runnable {
    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (tick > 0) {
            lock.lock();
            try {
                if (tick > 0) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " 售票：" + tick--);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); //释放
            }
        }
    }
}

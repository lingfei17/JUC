package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        for (int i = 0; i < 10; i++) {
            new Thread(test2).start();
        }
    }
}

class Test2 implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i.getAndIncrement());
    }
}

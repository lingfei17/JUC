package juc;

import java.util.concurrent.CountDownLatch;

// 閉鎖
// 可以用来确保某些操作执行完后 再运行其他操作
// await 等待计数为0时，继续往下运行
// countDown 计数减一
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }
        try {
            countDownLatch.await(); //await
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time：" + (end - start));

    }
}

class LatchDemo implements Runnable {
    private CountDownLatch countDownLatch;

    public LatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                countDownLatch.countDown();//countDown
            }
        }

    }
}

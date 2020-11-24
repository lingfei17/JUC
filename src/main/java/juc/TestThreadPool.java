package juc;

import java.util.concurrent.*;

/**
 * @author fei.ling
 * @desc 线程池
 * @create 2020-11-23 22:12
 **/
public class TestThreadPool {


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);


  /*      for (int i = 0; i < 10; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+ " newFixedThreadPool");
                }
            });
        }*/

        Future<Integer> integerFuture = pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        Integer integer = null;
        try {
            integer = integerFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(integer);

        pool.shutdown();

    }
}

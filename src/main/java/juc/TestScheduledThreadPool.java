package juc;

import java.util.concurrent.*;

/**
 * @author fei.ling
 * @desc
 * @create 2020-11-23 23:33
 **/
public class TestScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);


        for (int i = 0; i < 5; i++) {
            ScheduledFuture<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 1;
                }
            }, 3, TimeUnit.SECONDS);


            Integer integer = null;
            try {
                integer = result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(integer);
        }


        pool.shutdown();
    }
}

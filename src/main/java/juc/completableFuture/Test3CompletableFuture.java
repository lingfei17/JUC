package juc.completableFuture;

import juc.threadPool.MyThreadPool;

import java.util.concurrent.*;

public class Test3CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pool = MyThreadPool.getPool();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("執行任務1111111111111111111");
            return 1;
        }, pool);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("執行任務2222222222222222222");
            return 2;
        }, pool);

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("執行任務3333333333333333333");
            return 3;
        }, pool);

        CompletableFuture.allOf(future1, future2, future3);

        Integer r1 = future1.get();
        Integer r2 = future2.get();
        Integer r3 = future3.get();

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
    }
}

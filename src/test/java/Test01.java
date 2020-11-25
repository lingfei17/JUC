import juc.threadPool.MyThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiFunction;

public class Test01 {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = MyThreadPool.getPool();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("run thread 1-------------------------------");
            return 1;
        }, pool);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("run thread 2-------------------------------");
            return 2;
        }, pool);

        CompletableFuture<String> objectCompletableFuture = future1.thenCombineAsync(future2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                System.out.println(integer);
                System.out.println(integer2);
                return "abc";
            }
        }).handle((res, e) -> {
            System.out.println(res);
            System.out.println(e);
            return res;
        });


        pool.shutdown();
    }
}

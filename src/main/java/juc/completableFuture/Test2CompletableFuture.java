package juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2CompletableFuture {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);//創建綫程池

        //创建1 无返回值
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("任務内容執行---");
        },pool);*/

        //创建2 有返回值 任務完成后的處理
        /*CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任務内容執行---");
            return 1 / 0;
        }, pool).whenComplete((res, exception) -> {  //whenComplete 結果、異常  無返回值
            System.out.println(res);
            System.out.println(exception);
            System.out.println("任務執行完成---");
        }).exceptionally(t -> {                      //exceptionally 異常 有返回值
            System.out.println(t);
            return 1;
        });

        Integer result = completableFuture.get();
        System.out.println(result);*/


        //创建2 有返回值 任務完成后的處理
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任務内容執行---");
            return 1 / 0;
        }, pool).handle((res,exception)->{  //handle 結果、異常  有返回值
            System.out.println(res);
            System.out.println(exception);
            return 0;
        });




    }
}

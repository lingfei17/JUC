package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//创建执行线程的方式3
//实现callable接口方式
public class Test3Callable {

    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();

        //FutureTask接受结果
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);

        new Thread(futureTask).start();

        Integer integer = null;
        try {
            integer = futureTask.get(); // 在线程执行结果未返回时，线程等待

            System.out.println("end --- ");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + integer);
    }

}


class CallableDemo implements Callable<Integer> {

    //有返回值 并且抛出异常
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1;
    }
}

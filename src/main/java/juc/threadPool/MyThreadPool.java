package juc.threadPool;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    private static final int SIZE_CORE_POOL = 5;//核心线程数

    private static final int SIZE_MAX_POOL = 10;//最大线程数

    private static final long ALIVE_TIME = 2000;

    private static BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);//緩存隊列

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MICROSECONDS, blockingQueue, new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        pool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getPool() {
        return pool;
    }

}

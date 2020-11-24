package juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author fei.ling
 * @desc 拆分
 * @create 2020-11-23 23:42
 **/
public class TestForkJoinPool {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Long> forkJoin = new ForkJoin(0L, 10000000L);

        Long sum = forkJoinPool.invoke(forkJoin);

        System.out.println(sum);
    }

}

class ForkJoin extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long Thurshold = 10000L; //临界值


    public ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= Thurshold) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoin forkJoin1 = new ForkJoin(start, middle);
            forkJoin1.fork();

            ForkJoin forkJoin2 = new ForkJoin(middle + 1, end);
            forkJoin2.fork();

            return forkJoin1.join() + forkJoin2.join();
        }
    }
}

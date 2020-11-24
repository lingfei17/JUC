package juc;

import java.util.concurrent.locks.StampedLock;

//优化的读写锁测试
public class Test7StampedLock {

    public static void main(String[] args) {

    }
}

class StampLockDemo {
    private final StampedLock stampedLock = new StampedLock();

    private int x;
    private int y;


    /*注意到首先我们通过tryOptimisticRead()获取一个乐观读锁，并返回版本号。接着进行读取，读取完成后，
    我们通过validate()去验证版本号，如果在读取过程中没有写入，版本号不变，验证成功，我们就可以放心地继续后续操作。
    如果在读取过程中有写入，版本号会发生变化，验证将失败。在失败的时候，我们再通过获取悲观读锁再次读取。
    由于写入的概率不高，程序在绝大部分情况下可以通过乐观读锁获取数据，极少数情况下使用悲观读锁获取数据。*/
    public int read() {
        long stamp = stampedLock.tryOptimisticRead();  /// 获得一个乐观读锁
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        int currentX = x;      // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        int currentY = y;   // 此处已读取到y，如果没有写入，读取是正确的(100,200)    // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) {  // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return x + y;
    }

    public void write() {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += 10;
            y -= 20;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }
}

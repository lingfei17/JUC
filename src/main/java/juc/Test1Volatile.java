package juc;

//内存可见性
public class Test1Volatile {

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test).start();

        while (true) {
            if (test.isFlag()) {
                System.out.println("------");
                break;
            }
        }

    }
}


class Test implements Runnable {

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag: " + isFlag());
    }
}

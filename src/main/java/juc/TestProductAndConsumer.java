package juc;

//生產者消費者案例
public class TestProductAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Product product = new Product(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(product, "生產A").start();
        new Thread(consumer, "消費B").start();

        new Thread(product, "生產C").start();
        new Thread(consumer, "消費D").start();
    }
}

/*

//店员
class Clerk {
    private int product = 0;

    //进货
    public synchronized void get() {
        while (product >= 1) {
            System.out.println("已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 可以进货：" + ++product);
        this.notifyAll();
    }

    //卖货
    public synchronized void sell() {
        while (product <= 0) {
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 可以卖货：" + --product);
        this.notifyAll();
    }
}

//生產者
class Product implements Runnable {
    private Clerk clerk;

    public Product(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

//消費者
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sell();
        }
    }
}


*/



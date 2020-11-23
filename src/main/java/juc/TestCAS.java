package juc;

public class TestCAS {

    public static void main(String[] args) {
        final Cas cas = new Cas();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int value = cas.getValue();
                    boolean b = cas.compareAndSet(value, (int) (Math.random() * 10));
                    System.out.println(b);
                }
            }).start();
        }
    }
}

class Cas {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        return expectValue == compareAndSwap(expectValue, newValue);
    }
}
package juc;

/**
 * @author fei.ling
 * @desc 线程8锁
 * 判断打印输出的值
 * @create 2020-11-23 21:51
 * <p>
 * //1  两个同步方法 标准打印 === one two
 * //2  在getOne中添加Thread.sleep 打印 === one two
 * //3  添加普通方法 getThree 打印 === three one two
 * //4  两个同步方法 两个Number对象 number1调用getOne number2调用getTwo 打印 === two one
 * //5  getOne 改为静态同步方法 一个Number对象  调用getOne getTwo  打印 === two one
 * //6  getOne getTwo 改为静态同步方法 一个Number对象  调用getOne getTwo  打印 === one two
 * //7  getOne 改为静态同步方法 两个Number对象  分别调用getOne getTwo  打印 === two one
 * //8  getOne getTwo 改为静态同步方法 两个Number对象  分别调用getOne getTwo  打印 === one two
 *
 * // 区分对象锁this  与  类锁(静态同步方法)
 **/
public class TestThread8 {


    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number1.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                number1.getTwo();
                number2.getTwo();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number.getThree();
//            }
//        }).start();
    }

}


class Number {

    public static synchronized void getOne() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }
}

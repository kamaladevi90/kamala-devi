package datastructures;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    private CountDownLatch latch1 = new CountDownLatch(1);
    private CountDownLatch latch2 = new CountDownLatch(1);

    public CountDownLatchExample() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first"
        printFirst.run();
        latch1.countDown(); // signal that first() is done
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latch1.await(); // wait until first() is completed
        // printSecond.run() outputs "second"
        printSecond.run();
        latch2.countDown(); // signal that second() is done
    }

    public void third(Runnable printThird) throws InterruptedException {
        latch2.await(); // wait until second() is completed
        // printThird.run() outputs "third"
        printThird.run();
    }
    public static void main(String[] args) {
        CountDownLatchExample foo = new CountDownLatchExample();

        // Example Input
        int[] nums = {1, 3, 2};  
        // You can change to {1,2,3} or any order

        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        for (int n : nums) {
            if (n == 1) {
                new Thread(() -> {
                    try {
                        foo.first(printFirst);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else if (n == 2) {
                new Thread(() -> {
                    try {
                        foo.second(printSecond);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else if (n == 3) {
                new Thread(() -> {
                    try {
                        foo.third(printThird);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

}
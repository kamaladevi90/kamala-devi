package datastructures;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/*
 * A Semaphore is a synchronization construct used in multithreading to control access to shared resources. It’s a key concept in concurrent programming. Let me explain clearly:

1️⃣ Definition

A Semaphore maintains a set of permits. Threads can acquire or release permits:

Acquire: Decreases the number of available permits. If no permits are available, the thread blocks until one is released.

Release: Increases the number of available permits, potentially unblocking waiting threads.

Think of it as a traffic signal for threads accessing resources.

2️⃣ Types of Semaphores

Counting Semaphore

Allows multiple threads to access a limited number of resources.

Example: a pool of 5 database connections → Semaphore with 5 permits.

Binary Semaphore

Only 0 or 1 permit (like a lock).

Ensures mutual exclusion for a critical section.
 */
public class SemaphoreExampleMain {
    public static void main(String[] args) {
        int n = 50; // Change n as needed
        SemaphoreExample zeo = new SemaphoreExample(n);

        Thread tZero = new Thread(() -> {
            try {
                zeo.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread tEven = new Thread(() -> {
            try {
                zeo.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread tOdd = new Thread(() -> {
            try {
                zeo.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tZero.start();
        tEven.start();
        tOdd.start();
    }
}

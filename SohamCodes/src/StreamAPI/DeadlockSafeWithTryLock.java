package StreamAPI;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class DeadlockSafeWithTryLock {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void method() {
        try {
            if (lock1.tryLock(500, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " locked lock1");
                try {
                    if (lock2.tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + " locked lock2");
                        // do work
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not lock lock2, avoiding deadlock");
                    }
                } finally {
                    lock2.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not lock lock1, avoiding deadlock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();
        }
    }

    // Using tryLock() ensures threads back off instead of waiting forever, breaking deadlocks.
    public static void main(String[] args) {
        DeadlockSafeWithTryLock obj = new DeadlockSafeWithTryLock();

        Thread t1 = new Thread(obj::method, "Thread-1");
        Thread t2 = new Thread(obj::method, "Thread-2");

        t1.start();
        t2.start();
    }
}

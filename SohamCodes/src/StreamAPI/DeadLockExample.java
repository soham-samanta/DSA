package StreamAPI;

public class DeadLockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) { // Thread-1 acquires lock1
            System.out.println(Thread.currentThread().getName() + " locked lock1");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }

            synchronized (lock2) { // Thread-1 tries to acquire lock2 (already held by Thread-2)
                System.out.println(Thread.currentThread().getName() + " locked lock2");
            }
        }
    }

    public void method2() {
        synchronized (lock2) { // Thread-2 acquires lock2
            System.out.println(Thread.currentThread().getName() + " locked lock2");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }

            synchronized (lock1) { // Thread-2 tries to acquire lock1 (already held by Thread-1)
                System.out.println(Thread.currentThread().getName() + " locked lock1");
            }
        }
    }



    // psvm
    public static void main(String[] args) {
        DeadLockExample obj = new DeadLockExample();

        Thread t1 = new Thread(obj::method1, "Thread-1");
        Thread t2 = new Thread(obj::method2, "Thread-2");

        t1.start();
        t2.start();
    }

    /*
     - Thread-1 enters method1(), locks lock1, then waits to get lock2.
     - At the same time, Thread-2 enters method2(), locks lock2, then waits to get lock1.
     - Both threads are waiting for each other’s lock forever → Deadlock.
    */


}

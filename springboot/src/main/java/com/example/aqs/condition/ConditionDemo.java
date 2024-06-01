package com.example.aqs.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;

    public void increment() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);
        try {
            while (count == 10) {
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    demo.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                demo.decrement();
            }
        }, "B").start();
    }
}

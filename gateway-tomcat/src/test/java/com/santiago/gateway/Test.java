package com.santiago.gateway;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Test {
    private static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Thr(), "t0").start();
        new Thread(new Thr(), "t1").start();
        new Thread(new Thr(), "t2").start();
    }

    static class Thr implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

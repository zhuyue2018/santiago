package com.santiago.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-16 18:21
 **/
public class MockDeadLock {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();
    public static void getRes1() throws InterruptedException {
        synchronized(resource1) {
            System.out.println("get resources1");
            Thread.sleep(1000L);
            synchronized(resource2) {
                System.out.println("get resources2");
            }
        }
    }

    public static void getRes2() throws InterruptedException {
        synchronized(resource2) {
            System.out.println("get resources2");
            Thread.sleep(1000L);
            synchronized(resource1) {
                System.out.println("get resources1");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Dead()).start();
        new Thread(new Dead2()).start();
    }

    static class Dead implements Runnable {
        @Override
        public void run() {
            try {
                getRes1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Dead2 implements Runnable {
        @Override
        public void run() {
            try {
                getRes2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

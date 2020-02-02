package com.santiago.cg.controller;

import com.google.common.util.concurrent.UncaughtExceptionHandlers;

public class Test3 {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                throw new RuntimeException();
            }
        });
        thread.setUncaughtExceptionHandler(UncaughtExceptionHandlers.systemExit());
        thread.start();
        System.out.println(2);
    }
}

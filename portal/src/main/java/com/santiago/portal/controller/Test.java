package com.santiago.portal.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-18 10:15
 **/
public class Test {
    private static int maxValue;
    private static AtomicInteger value;

    public Test(int initialValue, int maxValue) {
        this.value = new AtomicInteger(initialValue);
        this.maxValue = maxValue;
    }
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        new Test(0, 99);
        threadPool.submit(new TT());
        threadPool.submit(new TT());
    }

    static class TT implements Runnable {
        @Override
        public void run() {
            int millisOfDay = LocalTime.now().getMillisOfDay();
            for (int i = 0 ; i < 1000000 ; i ++) {
                incrementAndGet();
            }
        }
    }

    public static final int incrementAndGet() {
        boolean b = value.compareAndSet(maxValue, 0);
        int i = value.incrementAndGet();
        return i;
    }

    private static String getLoginIp() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while(e.hasMoreElements()) {
                NetworkInterface item = (NetworkInterface)e.nextElement();
                Iterator i$ = item.getInterfaceAddresses().iterator();

                while(i$.hasNext()) {
                    InterfaceAddress address = (InterfaceAddress)i$.next();
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address)address.getAddress();
                        if (!inet4Address.isLinkLocalAddress() && !inet4Address.isLoopbackAddress() && !inet4Address.isMCGlobal() && !inet4Address.isMulticastAddress()) {
                            return inet4Address.getHostAddress();
                        }
                    }
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return "";
    }
}

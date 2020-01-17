package com.santiago.commons.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: dependency
 * @description:使用老的时间类，使用threadLocal
 * @author: zhuyue
 * @create: 2020-01-16 09:16
 **/
public class OldDateUtil {
    private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<>();
    public static void setDateFormat(SimpleDateFormat dateFormat) {
        dateFormatThreadLocal.set(dateFormat);
    }
    public static String getCurrentTimeStr() {
        SimpleDateFormat simpleDateFormat = dateFormatThreadLocal.get();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return simpleDateFormat.format(new Date());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                5,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        List<Future<String>> futureList = new ArrayList<>(16);
        for (int i = 0 ; i < 10 ; i ++) {
            if (i % 2 == 0) {
                futureList.add(threadPoolExecutor.submit(new TestThread("yyyy-MM-dd")));
            } else {
                futureList.add(threadPoolExecutor.submit(new TestThread("yyyy-MM-dd HH:mm:ss")));
            }
        }
        for (int j = 0 ; j < futureList.size() ; j ++) {
            String s = futureList.get(j).get();
            System.out.println(s);
        }
        System.out.println("finished");
    }
}

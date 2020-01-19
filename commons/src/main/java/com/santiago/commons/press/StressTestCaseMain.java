package com.santiago.commons.press;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StressTestCaseMain {
    private static List<StressTestCaseResult> listThreadResult = Collections.synchronizedList(new ArrayList());
    private static long serial = 0L;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
    private static SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
    private static DecimalFormat df = new DecimalFormat("00000000");

    public StressTestCaseMain() {
    }

    public static synchronized String generateSerialNo() {
        Date date = new Date();
        ++serial;
        if (serial > 99999999L) {
            serial = 1L;
        }

        return sdf.format(date) + df.format(serial);
    }

    public static synchronized String generateShortSerialNo() {
        Date date = new Date();
        ++serial;
        if (serial > 99999999L) {
            serial = 1L;
        }

        return sdfShort.format(date) + df.format(serial);
    }

    public static void testMain(List<IStressTest> listTestCase, long[] threadCount, long[] loopNumber) {
        StringBuffer sb = new StringBuffer();
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        try {
            Iterator var6 = listTestCase.iterator();

            while(var6.hasNext()) {
                IStressTest testCase = (IStressTest)var6.next();
                sb.append("\n=======================================================");
                sb.append("\n测试类 ,并发线程数 ,每个线程执行次数 ,耗时 ,每秒执行次数,总执行次数,异常次数\n");
                List<StressTestCaseResult> listSumResult = new ArrayList();

                for(int i = 0; i < threadCount.length; ++i) {
                    for(int j = 0; j < loopNumber.length; ++j) {
                        List<Future<StressTestCaseResult>> listFuture = new ArrayList();

                        for(int exe = 0; (long)exe < threadCount[i]; ++exe) {
                            StressTestCaseCallable test = new StressTestCaseCallable(testCase, threadCount[i], loopNumber[j]);
                            Future<StressTestCaseResult> future = threadPool.submit(test);
                            submitFuture(threadPool, future);
                            listFuture.add(future);
                        }

                        waitForAllDone(listFuture);
                        StressTestCaseResult sumResult = printResult(sb);
                        listSumResult.add(sumResult);
                    }
                }

                printSumResult(sb, listSumResult);
            }

            System.out.println(sb.toString());
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        threadPool.shutdown();
    }

    private static void waitForAllDone(List<Future<StressTestCaseResult>> listFuture) {
        boolean allDone = true;

        do {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            allDone = true;

            for(int exe = 0; exe < listFuture.size(); ++exe) {
                if (!((Future)listFuture.get(exe)).isDone()) {
                    allDone = false;
                    break;
                }
            }
        } while(!allDone);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    private static void submitFuture(ExecutorService threadPool, final Future<StressTestCaseResult> future) {
        (new Thread(new Runnable() {
            public void run() {
                StressTestCaseResult result = null;

                try {
                    result = (StressTestCaseResult)future.get();
                    StressTestCaseMain.listThreadResult.add(result);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        })).start();
    }

    private static synchronized StressTestCaseResult printResult(StringBuffer sb) {
        long count = (long)listThreadResult.size();
        long exceptionCount = 0L;
        StressTestCaseResult sum = new StressTestCaseResult();

        for(int i = 0; (long)i < count; ++i) {
            StressTestCaseResult result = (StressTestCaseResult)listThreadResult.get(i);
            sum.setClazz(result.getClazz());
            sum.setThreadCount(result.getThreadCount());
            sum.setLoopNumber(result.getLoopNumber());
            long startFirst = System.currentTimeMillis();
            if (result.getStart() <= startFirst) {
                startFirst = result.getStart();
                long usedTime = System.currentTimeMillis() - startFirst;
                sum.setUsedTime(usedTime);
            }

            if (result.isException()) {
                ++exceptionCount;
            }
        }

        sum.setExeCount(sum.getThreadCount() * sum.getLoopNumber());
        if (exceptionCount > 0L) {
            sum.setException((Exception)null);
            sum.setExceptionCount(exceptionCount);
        }

        if (sum.getUsedTime() <= 0L) {
            sum.setUsedTime(1L);
        }

        long per = Math.round((double)(sum.getThreadCount() * sum.getLoopNumber()) * 1000.0D / (double)sum.getUsedTime());
        if (count > 0L) {
            sum.setRunPerSecond(per);
        } else {
            sum.setRunPerSecond(0L);
        }

        sb.append(sum.getClazz().getSimpleName() + "," + sum.getThreadCount() + "," + sum.getLoopNumber() + "," + (double)sum.getUsedTime() / 1000.0D + "," + sum.getRunPerSecond() + "," + sum.getExeCount() + "," + sum.getExceptionCount() + "\n");
        return sum;
    }

    private static synchronized void printSumResult(StringBuffer sb, List<StressTestCaseResult> list) {
        String strClazz = null;
        long exeCount = 0L;
        double usedTime = 0.0D;
        long exceptionCount = 0L;

        for(int i = 0; i < list.size(); ++i) {
            StressTestCaseResult sumResult = (StressTestCaseResult)list.get(i);
            strClazz = sumResult.getClazz().getSimpleName();
            exeCount += sumResult.getThreadCount() * sumResult.getLoopNumber();
            usedTime += (double)sumResult.getUsedTime();
            exceptionCount += sumResult.getExceptionCount();
        }

        if (usedTime <= 0.0D) {
            usedTime = 1.0D;
        }

        usedTime /= 1000.0D;
        long perSecond = Math.round((double)exeCount / usedTime);
        sb.append(strClazz + ",总共执行[" + exeCount + "次],总共用时[" + usedTime + "秒],平均每秒执行[" + perSecond + "次]" + ",异常次数[" + exceptionCount + "次]" + (perSecond <= 20L ? ",【性能较差】" : ""));
        listThreadResult.clear();
    }
}

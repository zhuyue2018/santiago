package com.santiago.commons.press;

import java.util.concurrent.Callable;

class StressTestCaseCallable implements Callable<StressTestCaseResult> {
    IStressTest testCase = null;
    long thread = 1L;
    long loop = 1L;

    public StressTestCaseCallable(IStressTest testCase, long threadCount, long loopNumber) {
        this.testCase = testCase;
        this.thread = threadCount;
        this.loop = loopNumber;
    }

    public StressTestCaseResult call() {
        StressTestCaseResult result = new StressTestCaseResult();

        for(int i = 0; (long)i < this.loop; ++i) {
            try {
                if (this.testCase != null) {
                    this.testCase.testCase();
                }
            } catch (Exception var4) {
                var4.printStackTrace();
                result.setException(var4);
            }
        }

        result.setClazz(this.testCase.getClass());
        result.setThreadCount(this.thread);
        result.setLoopNumber(this.loop);
        return result;
    }
}

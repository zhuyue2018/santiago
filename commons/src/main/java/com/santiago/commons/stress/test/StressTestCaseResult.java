package com.santiago.commons.stress.test;

class StressTestCaseResult {
    private long threadCount = 0L;
    private long loopNumber = 0L;
    private long runPerSecond = 0L;
    private long usedTime = 0L;
    private Class<? extends IStressTest> clazz = null;
    private long start = System.currentTimeMillis();
    private boolean isException = false;
    private Exception exception;
    private long exceptionCount = 0L;
    private long exeCount = 0L;

    StressTestCaseResult() {
    }

    public long getThreadCount() {
        return this.threadCount;
    }

    public long getLoopNumber() {
        return this.loopNumber;
    }

    public long getRunPerSecond() {
        return this.runPerSecond;
    }

    public void setThreadCount(long threadCount) {
        this.threadCount = threadCount;
    }

    public void setLoopNumber(long loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void setRunPerSecond(long runPerSecond) {
        this.runPerSecond = runPerSecond;
    }

    public long getUsedTime() {
        return this.usedTime;
    }

    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }

    public Class<? extends IStressTest> getClazz() {
        return this.clazz;
    }

    public void setClazz(Class<? extends IStressTest> clazz) {
        this.clazz = clazz;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStart() {
        return this.start;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        this.isException = true;
        this.exception = exception;
    }

    public boolean isException() {
        return this.isException;
    }

    public long getExceptionCount() {
        return this.exceptionCount;
    }

    public void setExceptionCount(long exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public long getExeCount() {
        return this.exeCount;
    }

    public void setExeCount(long exeCount) {
        this.exeCount = exeCount;
    }
}

package com.santiago.commons.util;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceAtomicNumber {
    private int maxValue;
    private AtomicInteger value;

    public SequenceAtomicNumber(int maxValue) {
        this(0, maxValue);
    }

    public SequenceAtomicNumber(int initialValue, int maxValue) {
        this.value = new AtomicInteger(initialValue);
        this.maxValue = maxValue;
    }

    public final int incrementAndGet() {
        this.value.compareAndSet(this.maxValue, 0);
        return this.value.incrementAndGet();
    }

    public final int decrementAndGet() {
        this.value.compareAndSet(0, this.maxValue);
        return this.value.decrementAndGet();
    }
}
package com.santiago.commons.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerBound extends AtomicInteger {


    /**
     * @Fields field:field:原子int 边界操作
     */

    private static final long serialVersionUID = 7745859306834318793L;

    private int maxValue = 9999;


    public final int incrementAndGetWithBound() {
        for (; ; ) {
            int current = get();
            int next = current + 1;
            if (current >= maxValue) {
                set(0);
                continue;
            }
            if (compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public AtomicIntegerBound() {
        super();
    }

    public AtomicIntegerBound(int initialValue, int maxValue) {
        super(initialValue);
        this.maxValue = maxValue;
    }


}

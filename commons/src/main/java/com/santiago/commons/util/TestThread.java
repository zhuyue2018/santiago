package com.santiago.commons.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

import static com.santiago.commons.util.OldDateUtil.getCurrentTimeStr;

class TestThread implements Callable {
        TestThread(String pattern) {
            this.pattern = pattern;
        }
        String pattern;
        @Override
        public Object call() throws Exception {
            if (StringUtils.isEmpty(pattern)) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            OldDateUtil.setDateFormat(new SimpleDateFormat(pattern));
            return getCurrentTimeStr();
        }
    }
package com.santiago.gateway.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class DateUtil {
    public static final String yyyyMMdd = "yyyyMMdd";
    public static String parseDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    };

    public static void main(String[] args) {

    }
}

package com.santiago.commons.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: dependency
 * @description: 使用jdk1.8的日期工具类
 * @author: zhuyue
 * @create: 2020-01-17 13:59
 **/
public class NewDateUtils {
    private NewDateUtils() {
    }

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HHMMSS = "HHmmss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date getCurrentDate() {
        return Date.from(getCurrentInstant());
    }

    public static Instant getCurrentInstant() {
        return LocalDateTime.now().toInstant(ZoneOffset.ofHours(8));
    }

    public static OffsetDateTime getOffsetDateTime() {
        return OffsetDateTime.now();
    }

    public static String getCurrentTimeStr(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(getOffsetDateTime());
    }

    public static LocalDateTime getNextDay(LocalDateTime offset) {
        return offset.plusDays(1L);
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTimeStr(NewDateUtils.HHMMSS));
    }
}

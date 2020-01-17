package com.santiago.commons.util;

import java.math.BigDecimal;
import java.util.Date;
import net.sf.cglib.core.Converter;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericTypeConverter implements Converter {
    private static final Logger logger = LoggerFactory.getLogger(GenericTypeConverter.class);

    public GenericTypeConverter() {
    }
    @Override
    public Object convert(Object sourceValue, Class targetType, Object writeMethod) {
        String var4 = targetType.getSimpleName();
        byte var5 = -1;
        switch(var4.hashCode()) {
        case -1808118735:
            if (var4.equals("String")) {
                var5 = 2;
            }
            break;
        case 104431:
            if (var4.equals("int")) {
                var5 = 0;
            }
            break;
        case 2122702:
            if (var4.equals("Date")) {
                var5 = 1;
            }
            break;
        case 1438607953:
            if (var4.equals("BigDecimal")) {
                var5 = 3;
            }
        }

        switch(var5) {
        case 0:
            return this.otherToInt(sourceValue);
        case 1:
            return this.otherToDate(sourceValue);
        case 2:
            return this.otherToString(sourceValue);
        case 3:
            return this.otherToBigDecimal(sourceValue);
        default:
            logger.warn("{} cannot cast to {}", sourceValue.getClass().getName(), targetType.getSimpleName());
            return null;
        }
    }

    public int otherToInt(Object sourceValue) {
        try {
            if (!"int".equalsIgnoreCase(sourceValue.getClass().getName()) && !(sourceValue instanceof Integer)) {
                if (sourceValue instanceof String) {
                    return Integer.parseInt(String.valueOf(sourceValue));
                } else {
                    logger.warn("{} cannot cast to int", sourceValue.getClass().getName());
                    return 0;
                }
            } else {
                return (Integer)sourceValue;
            }
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
            return 0;
        }
    }

    public Date otherToDate(Object sourceValue) {
        try {
            if (sourceValue instanceof Date) {
                return (Date)sourceValue;
            } else if (sourceValue instanceof String) {
                return DateTime.parse((String)sourceValue).toDate();
            } else {
                logger.warn("{} cannot cast to Date", sourceValue.getClass().getName());
                return null;
            }
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
            return null;
        }
    }

    public String otherToString(Object sourceValue) {
        try {
            if (sourceValue instanceof Date) {
                return (new DateTime(sourceValue)).toString(ISODateTimeFormat.dateHourMinuteSecond());
            } else {
                return sourceValue instanceof BigDecimal ? ((BigDecimal)sourceValue).toPlainString() : sourceValue.toString();
            }
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
            return "";
        }
    }

    public BigDecimal otherToBigDecimal(Object sourceValue) {
        try {
            if (sourceValue instanceof BigDecimal) {
                return (BigDecimal)sourceValue;
            } else if (sourceValue instanceof String) {
                return new BigDecimal(String.valueOf(sourceValue));
            } else {
                logger.warn("{} cannot cast to BigDecimal", sourceValue.getClass().getName());
                return null;
            }
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
            return null;
        }
    }
}

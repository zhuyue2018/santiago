package com.santiago.commons.util;

import java.util.Map;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

public class BeanUtils {
    public BeanUtils() {
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static void copyProperties(Object source, Object target, boolean useConverter) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
        if (useConverter) {
            beanCopier.copy(source, target, new GenericTypeConverter());
        } else {
            beanCopier.copy(source, target, (Converter)null);
        }
    }
}
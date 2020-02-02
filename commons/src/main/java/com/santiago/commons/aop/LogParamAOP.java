package com.santiago.commons.aop;

import java.util.Arrays;

import com.santiago.commons.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogParamAOP {
    private static final Logger logger = LoggerFactory.getLogger(LogParamAOP.class);

    @Pointcut("@within(com.santiago.commons.annotation.LogParams)")
    public void pointcut() {}

    @Before("@annotation(com.santiago.commons.annotation.LogParams)")
    public void process(JoinPoint point) throws Throwable {
//        StringBuilder sb = new StringBuilder();
//        for (Object o : point.getArgs()) {
//            sb.append(JsonUtil.obj2JsonStrExcludeNull(o)).append(";");
//        }
        logger.info("params:method:[{}], param:[{}], target:[{}]",
                point.getSignature().getDeclaringTypeName() + ":" + point.getSignature().getName(),
                Arrays.toString(point.getArgs()),
                point.getTarget());
    }

   @AfterReturning(pointcut="@annotation(com.santiago.commons.annotation.LogParams)", returning="returnValue")
    public void log(JoinPoint point, Object returnValue) {
       logger.info("returns:method:[{}], return:[{}], target:[{}]",
               point.getSignature().getDeclaringTypeName() + ":" + point.getSignature().getName(),
               returnValue,
               point.getTarget());
    }
}
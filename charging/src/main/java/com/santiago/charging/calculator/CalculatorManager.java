package com.santiago.charging.calculator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value = "calc")
public class CalculatorManager extends Calculator implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Object calculate(String type) {
        String name = "calc-" + type;
        Calculator calculator = (Calculator)applicationContext.getBean(name);
        if (null == calculator) {
            throw new RuntimeException("此类型的计费器未加入spring管理");
        }
        return calculator.doCalculate();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object doCalculate() {
        return null;
    }
}

package com.santiago.charging.calculator;

import org.springframework.context.ApplicationContextAware;

public abstract class Calculator {
    public abstract Object doCalculate();
    public abstract Object calculate(String type);
}

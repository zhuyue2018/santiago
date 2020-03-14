package com.santiago.charging.calculator;

import org.springframework.stereotype.Component;

@Component(value = "calc-A")
public class ACalculator extends Calculator {
    @Override
    public Object doCalculate() {
        System.out.println("do a calc");
        return null;
    }

    @Override
    public Object calculate(String type) {
        return null;
    }
}

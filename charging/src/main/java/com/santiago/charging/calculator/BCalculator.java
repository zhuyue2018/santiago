package com.santiago.charging.calculator;

import org.springframework.stereotype.Component;

@Component(value = "calc-B")
public class BCalculator extends Calculator {
    @Override
    public Object doCalculate() {
        System.out.println("do b calc");
        return null;
    }

    @Override
    public Object calculate(String type) {
        return null;
    }
}

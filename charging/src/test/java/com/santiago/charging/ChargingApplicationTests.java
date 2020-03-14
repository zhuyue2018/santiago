package com.santiago.charging;

import com.santiago.charging.calculator.Calculator;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChargingApplicationTests {
    @Resource(name = "calc")
    Calculator calculator;

    @Test
    public void test00() {
        calculator.calculate("A");
        calculator.calculate("B");
    }
}

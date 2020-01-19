package com.santiago.commons.press;


import java.util.Random;

/**
 * 要测试的主Service的test单元测试类
 * 
 * @author cfzho
 *
 */
public class TestCaseDemo1 implements IStressTest {
	@Override
	public void testCase() throws Exception {
		// ------------------------------------
		// 这里放你想测并发的代码块
//		System.out.println(StressTestCaseMain.generateSerialNo());
//		System.out.println(StressTestCaseMain.generateShortSerialNo());
		double rnd = new Random().nextFloat();
		if (rnd >= 0.9) { // 异常概率
			throw new RuntimeException("异常:" + rnd);
		}
		// ------------------------------------
		// 测试序列号生成器 (如果需要生成序列号，可以用这个)
	}
}

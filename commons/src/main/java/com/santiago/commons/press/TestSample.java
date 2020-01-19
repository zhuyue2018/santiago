package com.santiago.commons.press;


import java.util.ArrayList;
import java.util.List;

public class TestSample {

	/**
	 * 示例代码
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 定义测试场景， 并发线程数，每个线程内循环次数
		long[] threadCount = { 10, 50 }; // 并发线程数 (可配置多个场景)
		long[] loopExecuteCount = { 1, 2 }; // 每个线程循环执行次数 （可配置多个场景）

		// 加入测试案例 (必须是定义了 ITestCase 接口的类 test()实现测试内容)
		List<IStressTest> listCase = new ArrayList<IStressTest>();
		listCase.add(new TestCaseDemo1());
		listCase.add(new TestCaseDemo2());

		// 启动测试
		StressTestCaseMain.testMain(listCase, threadCount, loopExecuteCount);

	}
}

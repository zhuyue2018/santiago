package com.santiago.cg.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Stack;

public class Test1 {
    static class Solution {
        public int[] dailyTemperatures(int[] T) {
            int[] ans = new int[T.length];
            Stack<Integer> stack = new Stack();
            for (int i = T.length - 1; i >= 0; --i) {
                while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
                ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[] i = {5,5,4,7,1,24,66,3};
        int[] ints = new Solution().dailyTemperatures(i);
        System.out.println(ints);
    }

}

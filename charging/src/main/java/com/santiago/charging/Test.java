package com.santiago.charging;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = 1; i<=Math.max(a.length(),b.length()) || carry >0; i++) {
            int c = a.length() >= i?a.charAt(a.length()-i)-48:0;
            int d = b.length() >= i?b.charAt(b.length()-i)-48:0;
            int sum = c+d+carry;
            if (sum>=2) {
                sb.append(sum-2);
                carry=1;
            } else {
                sb.append(sum);
                carry=0;
            }
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        System.out.println(new Test().addBinary("11", "1"));
    }
}

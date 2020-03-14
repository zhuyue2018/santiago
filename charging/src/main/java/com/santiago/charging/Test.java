package com.santiago.charging;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public List<Integer> spiralOrder(int[][] matrix) {
        int height = matrix.length;
        if (height == 0) {
            return null;
        }
        int width = matrix[0].length;
        List<Integer> list = new ArrayList<Integer>(height * width);
        int l = 0;
        int r = width - 1;
        int t = 0;
        int b = height - 1;
        while (r >= l && b >= t) {
            boolean flag = false;
            for (int i = l; i <= r; i++) {
                list.add(matrix[t][i]);
                flag = true;
            }
            if (flag) {
                t++;
                flag = false;
                for (int i = t; i <= b; i++) {
                    list.add(matrix[i][r]);
                    flag = true;
                }
                if (flag) {
                    r--;
                    flag = false;
                    for (int i = r; i >= l; i--) {
                        list.add(matrix[b][i]);
                        flag = true;
                    }
                    if (flag) {
                        b--;
                        flag = false;
                        for (int i = b; i >= t; i--) {
                            list.add(matrix[i][l]);
                            flag = true;
                        }
                        if (flag) {
                            l++;
                            flag = false;
                        }
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] i = {};
        new Test().spiralOrder(i);
    }
}

package com.santiago.cg.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static reactor.core.publisher.Flux.merge;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-16 12:53
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        /**
         * 外部排序算法
         */
        File file = new File("D:/outSort/outSort");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
        ) {
            StringBuilder inputStr = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                int randomInt = random.nextInt(100000);
                inputStr.append(randomInt).append(";");
            }
            fileOutputStream.write(inputStr.toString().getBytes());
        }
        int count = 0;
        int half = 10240;
        int max = 20480;
        try (RandomAccessFile file2 = new RandomAccessFile("D:/outSort/outSort", "r");) {
            int offset = 0;
            int seq = 0;
            while (true) {
                byte[] buffer = new byte[max];
                int read = file2.read(buffer);
                if (read > 0) {
                    count++;
                    String s = new String(buffer);
                    String s2 = s.substring(0, s.lastIndexOf(";"));
                    String[] arr = s2.split(";");
                    quickSort(arr, 0, arr.length - 1);
                    seq++;
                    String name = "D:/outSort/outSort-" + seq;
                    new File(name);
                    try (FileOutputStream fileInputStream = new FileOutputStream(new File(name));
                    ) {
                        for (String item : arr) {
                            fileInputStream.write(item.getBytes());
                            fileInputStream.write(";".getBytes());
                        }
                    }
                    offset += s2.length() + 1;
                    file2.seek(offset);
                } else {
                    break;
                }
            }
        }

        for (int i = 1; i <= count; i += 2) {
            try (FileInputStream fileInputStream = new FileInputStream("D:/outSort/outSort-" + i);
                 FileInputStream fileInputStream2 = new FileInputStream("D:/outSort/outSort-" + (i + 1));
            ) {
                byte[] buffer1 = new byte[max];
                byte[] buffer2 = new byte[max];
            }
        }
    }


    public static void quickSort(String[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = Integer.valueOf(arr[low]);

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= Integer.valueOf(arr[j]) && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= Integer.valueOf(arr[i]) && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = Integer.valueOf(arr[j]);
                arr[j] = arr[i];
                arr[i] = String.valueOf(t);
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = String.valueOf(temp);
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

    public void mergeSort(List<String> a) {
        int max = 0;
        for (; ; ) {
            max++;
            if (Math.pow(2, max) > a.size()) {
                break;
            }
        }
        int count = 0;
        while (count < max) {
            count++;
            int num = 0;
            while (num < a.size() / 2) {
                List<String> a0 = new ArrayList<>();
                List<String> a1 = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    a0.add(a.get(count + i - 1));
                    a1.add(a.get(count + i));
                }
                mergee(a0, a1);
            }
        }
    }


    private void mergee(List<String> a0, List<String> a1) {
        int i = 0;
        int j = 0;
        while (i < a0.size() || j < a1.size()) {
            String a = handle(a0.get(i), a1.get(j));
            if ("0".equals(a)) {
                i++;
            } else {
                j++;
            }
        }

    }

    private String handle(String s, String s1) {
//        ArrayList<Integer> target = new ArrayList<>();
//        ArrayList<Integer> intList0 = getByName(s);
//        ArrayList<Integer> intList1 = getByName(s1);
//        int i, j = 0;
//        while (true) {
//            if (intList0.get(i) > intList1.get(j)) {
//                target.add(intList1.get(j));
//                if (target.size() > 100) {
//                    target = new ArrayList<>();
//                }
//                j++;
//            } else {
//                target.add(intList0.get(i));
//                if (target.size() > 100) {
//                    target = new ArrayList<>();
//                }
//                i++;
//            }
//            if (i > intList0.size()) {
//                return "0";
//            }
//            if (j > intList0.size()) {
//                return "1";
//            }
//        }
        return null;
    }
}
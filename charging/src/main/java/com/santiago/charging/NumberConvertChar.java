package com.santiago.charging;

import java.util.ArrayList;
import java.util.List;

public class NumberConvertChar {
    
    private List<String> results = new ArrayList<>();
    
    public void number(int[] numbers){
        numbertochar(numbers,0,"");
    }
    
    private void numbertochar(int[] m,int start,String sequence) {
        if(start == m.length){
            results.add(sequence+coverttochar(m[start]));
        }
        //当前元素加入sequence
        String newsequence = sequence+coverttochar(m[start]);
        numbertochar(m,start+1,newsequence);
        //当前元素为2并且相邻元素小于6,考虑合并当前元素
        if (m[start]==2 && m[start+1]<=6 || m[start]==1) {
            //当前元素和后面一个元素一起加入
            String newsequence2 = sequence+coverttochar(m[start]*10+m[start+1]);
            if (start == m.length-1) {
                results.add(newsequence2);
            } else {
                numbertochar(m,start+2,newsequence2);
            }
        }
    }

    /**
     * 1-26转换成a-z
     * @param a
     * @return
     */
    private char coverttochar(int a) {
        return (char)('a'+a);
    }

    public static void main(String[] args) {
        int[] i = {1,2,1,2,1,2};
        new NumberConvertChar().number(i);
    }
}
package com.santiago.charging;

class Solution {
    public int lengthOfLastWord(String s) {
        boolean first = true;
        int length = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            if (32==s.charAt(i) && first) {
                continue;
            }
            if (32 == (s.charAt(i))){
                break;
            } else {
                first = false;
                length++;
            }
        }
        return length;

    }

    public static void main(String[] args) {
        int[] i = {-2,-1};
        System.out.println(new Solution().lengthOfLastWord("Hello World "));
    }
}
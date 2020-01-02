package com.mosaicsheep.kmp;

/**
 * 暴力破解字符串匹配
 *
 * @author LeslieTang
 * @version 2019/12/27
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        System.out.println(violenceMatch(str1, str2));
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int lenOfS1 = s1.length;
        int lenOfS2 = s2.length;

        int i = 0;// 指向s1的索引
        int j = 0;// 指向s2的索引

        while (i < lenOfS1 && j < lenOfS2) {
            if (s1[i] == s2[j]) {// 匹配成功，i和j同时后移
                i++;
                j++;
            } else {
                i = i - (j - 1);// 即i - j + 1， 回溯到原位置，再向前走一格
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == lenOfS2) {
            return i - j;
        } else {
            return -1;
        }
    }
}

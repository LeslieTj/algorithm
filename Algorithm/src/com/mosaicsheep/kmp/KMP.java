package com.mosaicsheep.kmp;

import java.util.Arrays;

/**
 * @author LeslieTang
 * @version 2019/12/28
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = KMPNext(str2);
        System.out.println(Arrays.toString(next));
        int index = KMPSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     * KMP解决字符串匹配问题
     *
     * @param str1 源字符串
     * @param str2 字串
     * @param next 字串对应的部分匹配表
     * @return -1表示没有匹配到，否则返回第一个匹配的位置
     */
    public static int KMPSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {// i指向str1，j指向str2

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {// 说明找到了
                return i - j + 1;
            }
        }

        return -1;
    }

    // 拿到部分匹配表
    public static int[] KMPNext(String dest) {
        // 创建next数组，保存部分匹配值
        int[] next = new int[dest.length()];

        // 1. 假设现在我们要创建AAAB的部分匹配表，字符串长度为1时，即A，部分匹配值为0，此时next为[0]
        // 2. 进入第一次循环：字符串长度为2时，即AA，前缀为[A]，后缀为[A]，即dest.charAt(1) == dest.charAt(0),部分匹配值加1，此时
        // next为[0,1]
        // 3. 进入第二次循环，i = 2，字符串长度为3，即AAA，此时j = 1， 前缀为[A],[AA],后缀为[A],[AA]，此时部分匹配值应该为2，我们
        // 验证一下：dest.charAt(2) == dest.charAt(1), 符合条件，因此j加1，部分匹配值为2。此时next为[0,1,2]
        // 4. 进入第三次循环，i = 3，字符串长度为4，即AAAB，此时j = 2，前缀为[A],[AA],[AAA],后缀为[B],[AB],[AAB],部分匹配值应
        // 该为0，我们来验证一下：
        //  4.1. dest.charAt(3) != dest.charAt(2)，因此j应该回退到next[j-1],即j = next[1] = 1
        //  4.2. dest.charAt(3) != dest.charAt(1), 因此j应该进一步回退到next[j-1]，即j = next[0] = 0
        //  4.3. 由于j = 0，退出while循环，此时next为[0,1,2,0]
        next[0] = 0;// 如果字符串长度为1，部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {// i = 1时，字符串长度为2

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;// 部分匹配值加1
            }
            next[i] = j;
        }
        return next;
    }
}

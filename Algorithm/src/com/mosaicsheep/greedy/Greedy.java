package com.mosaicsheep.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 使用贪婪算法解决集合覆盖问题
 *
 * @author LeslieTang
 * @version 2019/12/28
 */
public class Greedy {
    public static void main(String[] args) {
        HashMap<String, HashSet> broadcasts = new HashMap<>();

        HashSet<String> K1 = new HashSet<>();
        K1.add("北京");
        K1.add("上海");
        K1.add("天津");

        HashSet<String> K2 = new HashSet<>();
        K2.add("广州");
        K2.add("北京");
        K2.add("深圳");

        HashSet<String> K3 = new HashSet<>();
        K3.add("成都");
        K3.add("上海");
        K3.add("杭州");

        HashSet<String> K4 = new HashSet<>();
        K4.add("天津");
        K4.add("上海");

        HashSet<String> K5 = new HashSet<>();
        K5.add("杭州");
        K5.add("大连");

        broadcasts.put("K1", K1);
        broadcasts.put("K2", K2);
        broadcasts.put("K3", K3);
        broadcasts.put("K4", K4);
        broadcasts.put("K5", K5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("大连");
        allAreas.add("杭州");

        ArrayList<String> select = new ArrayList<>();

        // 创建一个临时集合，在遍历的过程中，存放电台覆盖的地区和当前没有覆盖的地区的交集
        HashSet<String> temp = new HashSet<>();

        // 定义一个maxKey，在一次遍历过程中，指向覆盖地区最多的电台
        String maxKey;

        // 双重循环，外层是while循环，内层是for循环
        while (allAreas.size() != 0) {// allAreas是不停地减少的
            // 每次循环前，将maxKey置空
            maxKey = null;

            // 遍历broadcasts
            for (String key : broadcasts.keySet()) {
                // 每次循环前都将temp置空
                temp.clear();
                HashSet<String> areas = broadcasts.get(key);// 当前电台能覆盖的地区
                temp.addAll(areas);// 先都加到临时集合里
                temp.retainAll(allAreas);// 和allAreas取交集

                // 如果当前集合包含的未覆盖地区的数量，比maxKey指向的未覆盖地区还多
                if (temp.size() > 0 &&
                        (maxKey == null || temp.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            // 遍历结束后，将选择的电台加入select
            if (maxKey != null) {
                select.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区从allAreas里面清除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }
            System.out.println("最终选择结果为：" + select);
    }
}

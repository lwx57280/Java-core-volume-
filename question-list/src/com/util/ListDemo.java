package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ListDemo {

    public static void main(String[] args) {
        List<String> colors = Stream.of("blue", "red", "yellow").collect(toList());

        List<String> colorList = Stream.of("blue", "red", "yellow","White").collect(toList());

        List<List<String>> list2 = new ArrayList<List<String>>() {{
            add(colors);
            add(colorList);
        }};
        // List<List<String>>转换list 拉平
        List<String> collectList = list2.stream().flatMap(strings -> strings.stream().map(i -> i + 1)).collect(toList());

        System.out.println("============collectList================");
        collectList.forEach(str -> System.out.println(str));
        // 统计集合元素个数
        long count = collectList.stream().count();
        System.out.println("============count================" + count);
        // 分组 计数
        Map<String, Long> stringLongMap = collectList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("============stringLongMap================" + count);

        // 等价于map.values().forEach(value -> System.out.println(value));
        stringLongMap.values().forEach(System.out::println);
        System.out.println("============v================");
        stringLongMap.values().forEach(v -> System.out.println(v));

        // 去重
        List<String> distinctElements = collectList.stream().distinct().collect(Collectors.toList());
        System.out.println("============distinctElements================");
        distinctElements.forEach(elements->System.out.println(elements));
    }
}

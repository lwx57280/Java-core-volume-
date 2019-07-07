package com.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @Description:    其他的流转换
 * @Author:         licongzhi
 * @CreateDate:     2019/7/7 18:17
 * @UpdateUser:    licongzhi
 * @UpdateDate:     2019/7/7 18:17
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class CreateStream {

    public static void main(String[] args) throws IOException {
        // 将字符串转换流按照顺序去重
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily","gently").distinct();
        uniqueWords.forEach(System.out::print);

        System.out.println();
        Path path = Paths.get("./chapter1.5/alice42.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // 将字符串转换流
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
        //   sorted：对流进行排序
        Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
        longestFirst.forEach(System.out::print);
        System.out.println();

        //
        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20).toArray();

        for (int i = 0; i < powers.length; i++) {
            System.out.println("powers:" + powers[i]);
        }

    }
}

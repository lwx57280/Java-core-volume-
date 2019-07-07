package com.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:    抽取子流和连接流
 * @Author:         licongzhi
 * @CreateDate:     2019/7/7 17:43
 * @UpdateUser:    licongzhi
 * @UpdateDate:     2019/7/7 17:43
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class StreamLimit {
    public static void main(String[] args) throws IOException {
        // 无限流 通过limit提取指定大小生成一个包含100个随机数的流
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
       // StreamLimit.show(randoms);

        Path path = Paths.get("./chapter1.3/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
       // words.forEach(System.out::println);
        // 将两个流连接起来
        Stream<String> combined = Stream.concat(letters("Hello"), letters("World"));
        combined.forEach(System.out::print);
    }

    private static void show(Stream<Double> stream){
        // println
        stream.forEach(System.out::println);
    }


    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }

    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 0;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.print(title + ":");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) {
                System.out.print(",");
            }
            if (i < SIZE) {
                System.out.print(firstElements.get(i));
            }else {
                System.out.print(".....");
            }
        }

    }
}

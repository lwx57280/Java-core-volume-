package com.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:    filter 、map和flatMap方法
 * @Author:         licongzhi
 * @CreateDate:     2019/7/7 12:45
 * @UpdateUser:    licongzhi
 * @UpdateDate:     2019/7/7 12:45
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class StreamDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./chapter1.3/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // 使用静态的Stream.of方法创建流
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        Stream<Stream<String>> result = words.stream().map(w -> letters(w));
        show("result",result);

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


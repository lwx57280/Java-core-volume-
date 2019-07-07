package com.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;
/**
 * @Description:    创建流的各种方式
 * @Author:         licongzhi
 * @CreateDate:     2019/7/7 12:12
 * @UpdateUser:    licongzhi
 * @UpdateDate:     2019/7/7 12:12
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class StreamMain {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./chapter1.2/alice.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // 使用静态的Stream.of方法创建流
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        CreatingStreams.show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        CreatingStreams.show("song", song);
        // 创建不包含任何元素的流，可以使用静态的Stream.empty方法
        Stream<Object> silence = Stream.empty();
        CreatingStreams.show("silence", silence);
        // 创建一个常量值的流
        Stream<String> echos = Stream.generate(() -> "Echo");
        CreatingStreams.show("echos",echos);
        // 创建一个随机数的流
        Stream<Double> randoms = Stream.generate(Math::random);
        CreatingStreams.show("randoms",randoms);
        // 创建一个种子序列流
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        CreatingStreams.show("integers",integers);

        // 创建正则分割流
        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);

        CreatingStreams.show("wordsAnotherWay",wordsAnotherWay);
        try (Stream<String> lines = Files.lines(path,StandardCharsets.UTF_8)){
            CreatingStreams.show("lines",lines);
        }
    }
}



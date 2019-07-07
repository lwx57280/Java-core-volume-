package com.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
/**
 * @Description:    从迭代到流的操作
 * @Author:         licongzhi
 * @CreateDate:     2019/7/7 11:38
 * @UpdateUser:    licongzhi
 * @UpdateDate:     2019/7/7 11:38
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class CountLongWords {

    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("" +
                "./chapter1/alice.txt")), StandardCharsets.UTF_8);

        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long count=0;
        for (String w:words){
            if(w.length()>12)
                count++;
        }
        System.out.println(count);
        // filter转换会产生一个流
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);


    }
}

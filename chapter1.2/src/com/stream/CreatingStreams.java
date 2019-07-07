package com.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 展示创建流的几种方式
 * @Author: licongzhi
 * @CreateDate: 2019/7/7 11:48
 * @UpdateUser: licongzhi
 * @UpdateDate: 2019/7/7 11:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class CreatingStreams {

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


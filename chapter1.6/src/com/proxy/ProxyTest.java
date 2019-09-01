package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @Description: 代理使用
 * @Author: li cong zhi
 * @CreateDate: 2019/9/1 17:51
 * @UpdateUser: li cong zhi
 * @UpdateDate: 2019/9/1 17:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Object[] elements = new Object[1000];
        // 用整数1 ... 1000的代理填充元素
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);

            elements[i] = proxy;
            // 构造一个随机整数
            Integer key = new Random().nextInt(elements.length) + 1;
            // 搜索密钥
            int result = Arrays.binarySearch(elements, key);
            // 如果找到则打印匹配
            if (result > 0) {
                System.out.println(elements[result]);
            }
        }
    }
}

/**
 * @Description: 打印出方法名称和参数的调用处理程序调用原始方法
 * @Author: li cong zhi
 * @CreateDate: 2019/9/1 17:55
 * @UpdateUser: li cong zhi
 * @UpdateDate: 2019/9/1 17:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 打印隐式参数
        System.out.print(target);
        // 打印方法名称
        System.out.print("." + method.getName() + "(");
        // 打印显式参数
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}
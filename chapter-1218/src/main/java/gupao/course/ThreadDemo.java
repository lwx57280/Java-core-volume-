package main.java.gupao.course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 1、如何控制线程执行的顺序
 * 	通过join方法去保证多线程的顺序性的特性，wait()
 * 	join:让主线程等待子线程结束以后才能继续运行。
 * @Author:         li cong zhi
 * @CreateDate:     2019/12/15 0:31
 * @UpdateUser:    li cong zhi
 * @UpdateDate:     2019/12/15 0:31
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class ThreadDemo {

    static Thread thread1 = new Thread(() -> System.out.println("thread1"));

    static Thread thread2 = new Thread(() -> System.out.println("thread2"));

    static Thread thread3 = new Thread(() -> System.out.println("thread3"));


    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
//        thread1.start();
//        thread1.join();
//        thread2.start();
//        thread2.join();
//        thread3.start();
//        thread3.join();
        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.shutdown();
    }
}

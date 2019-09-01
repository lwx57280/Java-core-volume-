package com.parallel;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * @Description:    演示了匿名内部类。
 * @Author:         li cong zhi
 * @CreateDate:     2019/9/1 16:48
 * @UpdateUser:    li cong zhi
 * @UpdateDate:     2019/9/1 16:48
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class AnonymousInnerClassTest {

    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000,true);
        // 保持程序运行，直到用户选择“确定”
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock{

    /**
     * 开始计时。
     * @author      li cong zhi
     * @param       interval 消息之间的间隔（以毫秒为单位）
     * @return
     * @exception
     * @date        2019/9/1 16:50
     */
    public void start(int interval,boolean beep){
        ActionListener listener = e -> {
            System.out.println("At the tone, the time is " + new Date());
            if(beep)
                Toolkit.getDefaultToolkit().beep();
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }
}

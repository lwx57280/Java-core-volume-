package com.param;

import com.domain.Employee;

/**
 * @Description:    java类作用描述
 * @Author:         li cong zhi
 * @CreateDate:     2019/9/1 16:01
 * @UpdateUser:    li cong zhi
 * @UpdateDate:     2019/9/1 16:01
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class ParamTest {

    public static void main(String[] args) {

        // 方法无法修改数字参数
        System.out.println("Testing tripleValue:");
        double percent=10;
        System.out.println("Before: percent=" + percent);
        tripleValue(percent);
        System.out.println("After: percent=" + percent);

        // 方法可以改变对象参数的状态
        System.out.println("\nTesting tripleSalary:");
        Employee harry = new Employee("Harry",50000);
        System.out.println("Before: salary=" + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary=" + harry.getSalary());

        //方法无法将新对象附加到对象参数
        System.out.println("\nTesting swap:");
        Employee a = new Employee("Alice", 70000);
        Employee b = new Employee("Bob", 60000);
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
        swap(a, b);
        System.out.println("After: a=" + a.getName());
        System.out.println("After: b=" + b.getName());

    }

    public static void swap(Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;
        System.out.println("End of method: x=" + x.getName());
        System.out.println("End of method: y=" + y.getName());
    }
   /**
    * 方法可以改变对象参数的状态
    * @author      li cong zhi
    * @param
    * @return
    * @exception
    * @date        2019/9/1 16:15
    */
    public static void tripleSalary(Employee e){
        e.raiseSalary(200);
        System.out.println("End of method: salary=" + e.getSalary());
    }
    public static void tripleValue(double x) {
        x = 3 * x;
        System.out.println("End of method: x=" + x);
    }
}

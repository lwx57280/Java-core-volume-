package com.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Car Cracker", 75000, 1987, 12, 16);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 16);
        staff[2] = new Employee("Tony Tester", 40000, 1987, 12, 16);

        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }
        try (Scanner in = new Scanner(new FileInputStream("employee.dat"),"UTF-8")){
            Employee[] newStaff = readData(in);
            for (Employee e:newStaff)
                System.out.println(e);
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {

        out.println(employees.length);

        for (Employee employee : employees) {
            writeEmployee(out, employee);
        }
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmploee(in);
        }
        return employees;
    }

    /**
     * 将员工数据写入打印作者
     *
     * @param
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/10/6 15:43
     */
    public static void writeEmployee(PrintWriter out, Employee e) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(e.getHireDay());
        out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) + "|"
                + (calendar.get(Calendar.MONTH) + 1 + "|"
                + calendar.get(Calendar.DAY_OF_MONTH)));

    }

    /**
     * 从缓冲的读取器读取员工数据
     *
     * @param in
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/10/6 15:46
     */
    public static Employee readEmploee(Scanner in) {
        String line = in.nextLine();

        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[3]);
        int day = Integer.parseInt(tokens[4]);
        return new Employee(name, salary, year, month, day);
    }
}

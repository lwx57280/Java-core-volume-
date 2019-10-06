package com.io;

import java.io.*;

public class FileInputDemo {

    public static void main(String[] args) throws IOException {

//        FileTest();

        //pushibackInputTest();

        DataInputStream dis = new DataInputStream(
                new PushbackInputStream(new BufferedInputStream(new FileInputStream("chapter1.7/alice.txt")))
        );
        byte b = dis.readByte();
        System.out.println("b: "+b);
    }

    private static void pushibackInputTest() throws IOException {
        PushbackInputStream pbin = new PushbackInputStream(
                new BufferedInputStream(
                        new FileInputStream("chapter1.7/alice.txt")
                )
        );

        int available = pbin.available();


        System.out.println("available= " + available);

        if ((available != '<'))
            pbin.unread(available);
    }

    private static void FileTest() throws IOException {
        FileInputStream inputStream = new FileInputStream("chapter1.7/alice.txt");

        DataInputStream din = new DataInputStream(inputStream);
        double v = din.read();

        System.out.println("v= " + v);
    }
}

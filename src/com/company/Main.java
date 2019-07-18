package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //IOException
        //FileNotFoundException
        //...


        OutputStream outputStream = new FileOutputStream("example.txt");
        String text = "my text to record";
        outputStream.write(text.getBytes());
        outputStream.close();


        InputStream inputStream = new FileInputStream("example.txt");
        //.read()                               ->  read one byte!
        //.read(byte[])
        //.read(byte[], int offset, int length)

        //-128 .. 127 -> 0..255 (unsigned int)

        //1. Java -> OS: want to read file blah-blah.txt
        //2. OS -> Java: file available, exists, ready to be read

        //+2 миллиарда  -    -2 миллиарда
        //int read = inputStream.read(); // -1

        //Example #1 | .read()
//        while (true) {
//            int myByte = inputStream.read();
//            if (myByte == -1) {
//                break;
//            }
//            System.out.println(myByte);
//        }

        //Example #2 | .read(byte[])
//        byte [] buffer = new byte[64 * 1024]; //64 KB
//        while (inputStream.available() > 0) {
//
//            int amountOfBytesRead = inputStream.read(buffer);
//            System.out.printf("Read %s bytes\n", amountOfBytesRead);
//
//            //available     - сколько байт можно прочитать ПРЯМО СЕЙЧАС
//            //read(byte[])
//
//            //inputStream.available() - 0
//            //inputStream.read(buffer);  - blocking call (блокирующий вызов)
//            //System.out.println("abc");
//        }
//
//        inputStream.close();
//        System.out.println(new String(buffer));


        //Example #3 | .read(byte[], int offset, int length)
//        byte [] buffer = new byte[100 * 1024 * 1024]; //100 MB
//        int bytesToSkip = 33;
//        int bytesAvailableForWrite = 33-1;
//        inputStream.read(buffer, bytesToSkip, bytesAvailableForWrite);


        copy(
                new FileInputStream("java-4h-io-1.iml"),
                new FileOutputStream("experiment-number-100500")
        );


        //Example #4 - file locking by OS (with and without .close()
//        OutputStream outputStreamLockExample = new FileOutputStream("example.txt");
//        int counter = 0;
//        while (true) {
//            counter++;
//            Thread.sleep(1000); //1 second
//            outputStreamLockExample.write(50);
//            if (counter > 20) {
//                break;
//            }
//        }
//
//        outputStreamLockExample.close();
//
//        System.out.println("Writing finished");
//        while (true) {
//            Thread.sleep(1000);
//        }

        //Example #5
        OutputStream outputStreamExample = new FileOutputStream("example.txt");

        byte [] buffer = new byte[10];
        buffer[0] = 50; //2
        buffer[1] = 60; //12
        outputStreamExample.write(buffer); // записать < 8 bytes
        outputStreamExample.flush();       // продавить
        outputStreamExample.close();


        //InputStream   -> byte []      End Of File
        //Reader        -> char []

        //OutputStream  -> byte[]
        //Writer        -> char[]

        //BufferedReader/BufferedWriter (String)
        //PrintReader   /PrintWriter    (many strings)

        //System.out.println(); -> PrintWriter
        //System.in; -> PrintReader
    }

    private static void copy(InputStream from, OutputStream to) throws IOException {
        byte [] buffer = new byte[100 * 1024];
        while (from.available() > 0) {
            from.read(buffer);
        }
        from.close();

        to.write(buffer);
        to.flush();
        to.close();
    }
}

package com.example.mylibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author ziv
 * @date 2018/5/9
 */

public class Test {

    public static void main(String[] args) {

        File parentFile = new File("haha/xixi/heh.txt");

        System.out.println(parentFile.exists());

        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(parentFile);
            FileInputStream fileInputStream = new FileInputStream(parentFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        System.out.println(parentFile.exists());

        File file = new File(parentFile, "hehe");

        System.out.println(file.exists());

//        if (!file.exists()) {
////            System.out.println(file.mkdirs());
////            System.out.println(file.exists());
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        System.out.println(file.getAbsolutePath());

    }

}

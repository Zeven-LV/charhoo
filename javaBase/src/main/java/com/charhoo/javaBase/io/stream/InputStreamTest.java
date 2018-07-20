package com.charhoo.javaBase.io.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputStreamTest {

    public static void main(String[] args) {

    }

    public void fileInputStreamTest(){
        try {
            FileInputStream fileInputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

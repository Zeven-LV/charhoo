package com.charhoo.javaBase.classload;

import java.io.File;

/**
 * loadClass，findClass，defineClass
 * loadClass（String name）；根据cn.com.akl.DemoController查找并加载类。先在parent或bootstrap中查找，有则给jvm加载。没有则按照findClass方法查找。
 * findClass（）；默认抛出一个ClassNotFoundException，如果需要自己重新覆盖实现。
 * defineClass（）；是将你定义的字节码文件经过字节数组流解密之后，将该字节流数组生成字节码文件，也就是该类的文件的类名.class。
 */
public class ClassLoaderTest {

    public void showClassLoad(String name){
        System.out.println("hello :"+name);
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        while(classLoader != null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }

    public static void main(String[] args) {
        System.out.println("1");
    }
}

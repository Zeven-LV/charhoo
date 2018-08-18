package com.charhoo.javaBase.classload;

/**
 * 在Java中，类装载器把一个类装入JVM中，要经过以下步骤：
 * (1) 装载：查找和导入Class文件；
 * (2) 链接：把类的二进制数据合并到JRE中；
 *     (a)校验：检查载入Class文件数据的正确性；
 *     (b)准备：给类的静态变量分配存储空间；
 *     (c)解析：将符号引用转成直接引用；
 * (3) 初始化：对类的静态变量，静态代码块执行初始化操作
 */
public class ClassLoaderTest02 {

    public static void main(String[] args) {
        /**
         * 对于静态字段，只有直接定义这个字段的类才会被初始化,因此，
         * 通过子类来调用父类的静态字段，只会触发父类的初始化,但是这是要看不同的虚拟机的不同实现
         */
//        System.out.println(SubClass.value);
//        System.out.println(SubClass.subValue);

        /**
         * 通过数组定义来引用类，不会触发此类的初始化
         */
//        SupperClass[] supperClasses = new SupperClass[10];

        /**
         * 常量在编译阶段会存入调用类的常量池中，本质上没有引用到定义常量的类，不会触发此类的初始化
         */
        System.out.println(ConstantClass.value);

    }
}

class SupperClass{
    static {
        System.out.println("---------SupperClass---------");
    }
    public static int value = 123;
}

class SubClass extends SupperClass{
    static {
        System.out.println("-----------SubClass------------");
    }

    public static int subValue = 321;
}

class ConstantClass{
    static{
        System.out.println("------------ConstantClass------------");
    }
    public static final String value = "qwe";
}

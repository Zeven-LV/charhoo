package com.charhoo.javaBase.ienum;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * 枚举的用法
 * 1，常量
 * 2，switch
 * 3，新方法
 * 4，覆盖原方法
 * 5，实现接口（枚举类都继承自ENUM）
 * 6，用接口组织枚举类
 * 7,枚举相关集合
 *
 */
public class EnumTest {

    public static void main(String[] args) {

        System.out.println(Color.RED.getName());

        for(Color color : Color.values()){
            System.out.println(color.getName());
        }

        System.out.println(Color.RED.toString());

        System.out.println(Food.Fruit.APPLE);

        /*EnumMap性能高！因为它的内部是用数组的数据结构来维护的
        * */
        EnumMap enumMap = new EnumMap(Color.class);
        enumMap.put(Color.RED,"红色");
        enumMap.put(Color.BLUE,"蓝色");
        System.out.println(enumMap);

        EnumSet enumSet = EnumSet.noneOf(Color.class);
        enumSet.add(Color.RED);
        System.out.println(enumSet);
    }
}

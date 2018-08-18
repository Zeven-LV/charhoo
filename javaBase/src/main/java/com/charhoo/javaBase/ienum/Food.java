package com.charhoo.javaBase.ienum;

/**
 * 接口组织枚举类
 */
public interface Food {

    enum Fruit implements Food{
        APPLE,ORANGE;
    }

    enum Vegetables implements Food{
        POTATO,TOMATOES;
    }

}

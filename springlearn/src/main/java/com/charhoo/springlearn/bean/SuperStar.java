package com.charhoo.springlearn.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;


/**
 * 自定义方法替换解决依赖作用域不同问题
 */
public class SuperStar implements MethodReplacer {

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        Person person = new Person();
        person.setName("lisi");
        return person;
    }
}

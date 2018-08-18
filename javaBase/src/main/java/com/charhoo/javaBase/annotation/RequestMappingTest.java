package com.charhoo.javaBase.annotation;

import java.lang.annotation.Annotation;

@RequestMapping(value="/user")
public class RequestMappingTest {

    @RequestMapping(value="/get", method = "post")
    public String get(){
        return "zhangsan";
    }

    public static void main(String[] args) {
        Class clazz = RequestMappingTest.class;
        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        System.out.println(requestMapping.value()[0]);
    }
}

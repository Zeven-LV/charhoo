package com.charhoo.javaBase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target 表示该注解可以用于什么地方
 * @Retention 表示需要在什么级别保存该注解信息。SOURCE,CLASS,RUNTIME
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase{
    public String id();
    public String description() default "no description";
}

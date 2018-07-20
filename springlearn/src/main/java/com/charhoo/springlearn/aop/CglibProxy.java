package com.charhoo.springlearn.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 只要符合Java　Class规范，我们也可以使用CGLib等Java工具类，在程序运行期间，动态构建字节码的class文件。
 * 在这样的前提下，我们可以为需要织入横切逻辑的模块类在运行期间，通过动态字节码增强技术，为这些系统模块类生成相应的子类，
 * 而将横切逻辑加到这些子类中，让应用程序在执行期间使用的是这些动态生成的子类，从而达到将横切逻辑织入系统的目的.
 * 使用动态字节码增强技术，即使模块类没有实现相应的接口，我们依然可以对其进行扩展，而不用像动态代理那样受限于接口。
 * 不过这种实现的不足是，如果需要扩展的类以及类中的实例方法等声名为final，则无法对其进行子类化扩展。
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(o.getClass().getName() + "." + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        PerformanceMonitor.end();
        return result;
    }
}

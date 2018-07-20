package com.charhoo.springlearn.aop;

public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> threadLocal = new ThreadLocal<>();

    public static void begin(String method){
        System.out.println("begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        threadLocal.set(mp);
    }

    public static void end() {
        System.out.println("end monitor...");
        MethodPerformance mp = threadLocal.get();
        mp.printPerformance();
    }


}

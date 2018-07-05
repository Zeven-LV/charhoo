package com.charhoo.javaBase.thread.threadlocal;

import java.text.SimpleDateFormat;

public class TreadLocalTest02 {

    private static ThreadLocal dateFormat = new ThreadLocal();

    private SimpleDateFormat getFormat(){
        SimpleDateFormat format = (SimpleDateFormat)dateFormat.get();
        if(format == null){
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        return format;
    }
}

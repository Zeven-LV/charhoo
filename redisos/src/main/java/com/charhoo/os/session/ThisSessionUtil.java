package com.charhoo.os.session;

import org.springframework.web.context.WebApplicationContext;

public class ThisSessionUtil {

    public static WebApplicationContext applicationContext;

    public static ThisSession getSession(){
        return ThreadContext.getSession();
    }
}

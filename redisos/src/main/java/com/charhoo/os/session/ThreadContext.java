package com.charhoo.os.session;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里借鉴shire
 */
public class ThreadContext {

    public static final String SESSION_KEY = ThreadContext.class.getName() + "_SESSION_KEY";
    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadContext.InheritableThreadLocalMap();

    public static ThisSession getSession(){
        return (ThisSession)get(SESSION_KEY);
    }

    public static Object get(Object key) {

        Object value = getValue(key);

        return value;
    }

    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = (Map)resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    public static void bind(ThisSession session){
        if(session != null){
            put(SESSION_KEY, session);
        }
    }

    private static void put(String key, ThisSession value){
        if(key == null){
            throw new IllegalArgumentException("key cannot be null");
        }else {
            ((Map)resources.get()).put(key, value);
        }
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        private InheritableThreadLocalMap() {
        }

        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            return parentValue != null ? (Map)((HashMap)parentValue).clone() : null;
        }
    }

}

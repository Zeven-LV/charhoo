package com.charhoo.spring.ioc;

import javax.xml.bind.Element;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
/*
    public void getXMLBean(Properties beanProperty){
        if (beanProperty.element("map") != null) {
            Map<String, Object> propertiesMap = new HashMap<>();
            Element propertiesListMap = (Element) beanProperty
                    .elements().get(0);
            Iterator<?> propertiesIterator = propertiesListMap
                    .elements().iterator();
            while (propertiesIterator.hasNext()) {
                Element vet = (Element) propertiesIterator.next();
                if (vet.getName().equals("entry")) {
                    String key = vet.attributeValue("key");
                    Iterator<?> valuesIterator = vet.elements()
                            .iterator();
                    while (valuesIterator.hasNext()) {
                        Element value = (Element) valuesIterator.next();
                        if (value.getName().equals("value")) {
                            propertiesMap.put(key, value.getText());
                        }
                        if (value.getName().equals("ref")) {
                            propertiesMap.put(key, new String[] { value
                                    .attributeValue("bean") });
                        }
                    }
                }
            }
            bean.getProperties().put(name, propertiesMap);
        }
    }

    public static Object newInstance(String className) {
        Class<?> cls = null;
        Object obj = null;
        try {
            cls = Class.forName(className);
            obj = cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static void setProperty(Object obj, String name, String value) {
        Class<? extends Object> clazz = obj.getClass();
        try {
            String methodName = returnSetMthodName(name);
            Method[] ms = clazz.getMethods();
            for (Method m : ms) {
                if (m.getName().equals(methodName)) {
                    if (m.getParameterTypes().length == 1) {
                        Class<?> clazzParameterType = m.getParameterTypes()[0];
                        setFieldValue(clazzParameterType.getName(), value, m,
                                obj);
                        break;
                    }
                }
            }
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getObject(Object value){
        if (value instanceof Map) {
            Iterator<?> entryIterator = ((Map<?, ?>) value).entrySet()
                    .iterator();
            Map<String, Object> map = new HashMap<String, Object>();
            while (entryIterator.hasNext()) {
                Map.Entry<?, ?> entryMap = (Map.Entry<?, ?>) entryIterator.next();
                if (entryMap.getValue() instanceof String[]) {
                    map.put((String) entryMap.getKey(),
                            getBean(((String[]) entryMap.getValue())[0]));
                }
            }
            BeanProcesser.setProperty(obj, property, map);
        }
        return null;
    }
*/
}

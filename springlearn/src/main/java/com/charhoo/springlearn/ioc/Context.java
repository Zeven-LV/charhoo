package com.charhoo.springlearn.ioc;

import com.charhoo.springlearn.bean.Person;
import com.charhoo.springlearn.bean.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;


/**
 * BeanFactory(getBean();containsBean();getType();isTypeMatch();isSingleton();isPrototype();)
 *      ListableBeanFactory
 *
 *      ClassPathXmlApplicationContext(setConfigLocations();refresh())
 *
 *
 *
 * 两种容器类型：BeanFactory与ApplicationContext区别
 * 1，是否需要手动注册BeanPostProcessor
 * 2，Bean实例化的时间不同，导致启动时占用的资源不同，启动用时不同
 * 3，applicationContext其他特性：事件发布，国际化信息
 */
public class Context {

    public static void main(String[] args) {

        Context context = new Context();
        context.beanFactoryTest();

    }

    public void applicationContextTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println("end loading xml");
        User user = (User) context.getBean("user");
        System.out.println(user);

        Person person = (Person)context.getBean("person1");
        System.out.println(person);
        ((ClassPathXmlApplicationContext) context).close();
    }

    public void beanFactoryTest(){
        ClassPathResource classPathResource = new ClassPathResource("application.xml");
        ConfigurableListableBeanFactory configurableListableBeanFactory = new XmlBeanFactory(classPathResource);
        //使用BeanFactory需要手动注册BeanPostProcessor
        configurableListableBeanFactory.addBeanPostProcessor(new MyBeanPostProcessor());
        System.out.println("end loading xml");

        User user = (User) configurableListableBeanFactory.getBean("user");
        System.out.println(user);

        Person person = (Person)configurableListableBeanFactory.getBean("person1");
        System.out.println(person);
        configurableListableBeanFactory.destroySingletons();
    }

    public void springContext(){
        BeanFactory beanFactory = null;
        ListableBeanFactory listableBeanFactory = null;

        BeanDefinition beanDefinition = null;
        BeanWrapper beanWrapper = new BeanWrapperImpl();
        BeanPostProcessor beanPostProcessor = null;

        ResourceLoader resourceLoader = null;
        ResourcePatternResolver resourcePatternResolver = null;
    }
}

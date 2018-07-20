package com.charhoo.springlearn.ioc;

import com.charhoo.springlearn.bean.Company;
import com.charhoo.springlearn.bean.Person;
import com.charhoo.springlearn.bean.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * bean的三种构建方式
 */
public class Bean {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
//        BeanFactory container  = bindViaCode(beanRegistry);
        BeanFactory container  = bindViaXmlFile(beanRegistry);
        User user = (User) container.getBean("user");
        user.getAndDoTask();

    }

    //编码方式构建bean
    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        AbstractBeanDefinition user = new RootBeanDefinition(User.class,RootBeanDefinition.AUTOWIRE_BY_TYPE, true);
        AbstractBeanDefinition person = new RootBeanDefinition(Person.class,RootBeanDefinition.AUTOWIRE_BY_TYPE, true);
        AbstractBeanDefinition company = new RootBeanDefinition(Company.class,RootBeanDefinition.AUTOWIRE_BY_TYPE, true);
        // 将bean定义到容器中
        registry.registerBeanDefinition("user",user);
        registry.registerBeanDefinition("person",person);
        registry.registerBeanDefinition("company",company);

        //指定依赖关系
        //1，通过构造方法注入
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0,person);
        argValues.addIndexedArgumentValue(1,company);
        user.setConstructorArgumentValues(argValues);

        //2，通过set方法注入
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","张三"));
        person.setPropertyValues(propertyValues);

        return (BeanFactory)registry;

    }

    /**
     * xml方式构建bean
     * bean的XML配置
     *      depands-on
     *      autowite:byName;byType;constructor;autodetect
     *      scope:singleton,prototype, request, session,global session
     * @param registry
     * @return
     */
    public static BeanFactory bindViaXmlFile(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("application.xml");
        return (BeanFactory)registry;

    }

    //注解方式

}

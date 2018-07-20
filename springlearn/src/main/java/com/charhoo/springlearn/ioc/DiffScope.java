package com.charhoo.springlearn.ioc;

import com.charhoo.springlearn.bean.Movie;
import com.charhoo.springlearn.bean.MovieStar;
import com.charhoo.springlearn.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 调用一个singleton类型bean A的某个方法时，需要引用另一个非singleton（prototype）类型的bean B
 * 解决方法：1，放弃控制反转；2，lookup-method方法注入；3，实现MethodReplacer，方法替换；
 * 分析：1不方便，3，代码入侵性强；故推荐用2
 */
public class DiffScope {

    public static void main(String[] args) {
//        lookupMethod();
        replaceMethod();
    }

    public static void lookupMethod(){
        ApplicationContext context = new ClassPathXmlApplicationContext("diffscope-application.xml");
        Movie movie = context.getBean("movie", Movie.class);
        Person p1 = movie.getPerson();
        Person p2 = movie.getPerson();
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }

    public static void replaceMethod(){
        ApplicationContext context = new ClassPathXmlApplicationContext("diffscope-application.xml");
        MovieStar p1 = new MovieStar();
        System.out.println(p1.getPerson().toString());
        MovieStar p2 = (MovieStar)context.getBean("movieStar");
        System.out.println(p2.getPerson().toString());
        MovieStar p3 = (MovieStar)context.getBean("movieStar");
        System.out.println(p3.getPerson().toString());
    }

}

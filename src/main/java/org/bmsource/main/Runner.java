package org.bmsource.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

public class Runner
{

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring/application-context.xml" });
        BeanNameUrlHandlerMapping x = context.getBean("defaultHandlerMapping", BeanNameUrlHandlerMapping.class);
        System.err.println(x);
    }

}

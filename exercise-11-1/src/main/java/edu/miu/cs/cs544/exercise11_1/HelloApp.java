package edu.miu.cs.cs544.exercise11_1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        Greeting greetingService = context.getBean("greetingService", Greeting.class);
        greetingService.sayHello();
    }
}

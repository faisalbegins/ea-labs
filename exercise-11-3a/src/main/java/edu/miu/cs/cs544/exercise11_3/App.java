package edu.miu.cs.cs544.exercise11_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class App {

    private final ApplicationContext context;

    @Autowired
    public App(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.miu.cs.cs544.exercise11_3");
        IBookService bookService = context.getBean("bookService", IBookService.class);

        bookService.buy(new Book("123433267", "Harry Potter and the Order of the Phoenix", "J.K. Rowling"));
        bookService.buy(new Book("888832678", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling"));
        bookService.buy(new Book("999923156", "Harry Potter and the Goblet of Fire", "J.K. Rowling"));
    }

    @Bean("suppliers")
    public List<IBookSupplier> allSuppliers() {
        return new ArrayList<>(context.getBeansOfType(IBookSupplier.class).values());
    }
}

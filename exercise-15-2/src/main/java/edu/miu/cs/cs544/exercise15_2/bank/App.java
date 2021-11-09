package edu.miu.cs.cs544.exercise15_2.bank;

import edu.miu.cs.cs544.exercise15_2.bank.domain.Account;
import edu.miu.cs.cs544.exercise15_2.bank.domain.AccountEntry;
import edu.miu.cs.cs544.exercise15_2.bank.domain.Customer;
import edu.miu.cs.cs544.exercise15_2.bank.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;


public class App {
    public static void main(String[] args) {
        // initialize spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-service-config.xml");
        // get account service from spring context
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        // create 2 accounts;
        accountService.createAccount(1263862, "Frank Brown");
        accountService.createAccount(4253892, "John Doe");
        //use account 1;
        accountService.deposit(1263862, 240);
        accountService.deposit(1263862, 529);
        accountService.withdrawEuros(1263862, 230);
        //use account 2;
        accountService.deposit(4253892, 12450);
        accountService.depositEuros(4253892, 200);
        accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
    }

}



package edu.miu.cs.cs544.exercise17_1.bank;

import edu.miu.cs.cs544.exercise17_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise17_1.bank.domain.AccountEntry;
import edu.miu.cs.cs544.exercise17_1.bank.domain.Customer;
import edu.miu.cs.cs544.exercise17_1.bank.service.AccountService;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        SessionFactory sf = context.getBean("sessionFactory", SessionFactory.class);
        AccountService accountService = context.getBean("accountService", AccountService.class);
        Transaction tx = sf.getCurrentSession().beginTransaction();
        try {
            // create 2 accounts;
            accountService.createAccount(1263862, "Frank Brown");
            accountService.createAccount(4253892, "John Doe");
            // use account 1;
            accountService.deposit(1263862, 240);
            accountService.deposit(1263862, 529);
            accountService.withdrawEuros(1263862, 230);
            // use account 2;
            accountService.deposit(4253892, 12450);
            accountService.depositEuros(4253892, 200);
            accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
            // show balances

            Collection<Account> accounts = accountService.getAllAccounts();
            Customer customer = null;
            for (Account account : accounts) {
                customer = account.getCustomer();
                System.out.println("Statement for Account: " + account.getAccountNumber());
                System.out.println("Account Holder: " + customer.getName());
                System.out.println(
                        "-Date-------------------------" + "-Description------------------" + "-Amount-------------");
                for (AccountEntry entry : account.getEntryList()) {
                    System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(),
                            entry.getAmount());
                }
                System.out.println("----------------------------------------" + "----------------------------------------");
                System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
            }
            tx.commit();
        } catch (RuntimeException exception) {
            try {
                exception.printStackTrace();
                tx.rollback();
            } catch (RuntimeException rbEx) {
                System.out.println("Could not rollback transaction " + rbEx.getMessage());
                rbEx.printStackTrace();
            }
        }
        System.exit(0);
    }
}

package edu.miu.cs.cs544.exercise15_2.bank.service;

import edu.miu.cs.cs544.exercise15_2.bank.dao.IAccountDAO;
import edu.miu.cs.cs544.exercise15_2.bank.domain.Account;
import edu.miu.cs.cs544.exercise15_2.bank.domain.AccountEntry;
import edu.miu.cs.cs544.exercise15_2.bank.domain.Customer;
import edu.miu.cs.cs544.exercise15_2.bank.jms.IJMSSender;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class AccountService implements IAccountService {
    private final IAccountDAO accountDAO;
    private final ICurrencyConverter currencyConverter;
    private final IJMSSender jmsSender;

    public AccountService(IAccountDAO accountDAO,
                          ICurrencyConverter currencyConverter,
                          IJMSSender jmsSender) {
        this.accountDAO = accountDAO;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
    }

    public Account createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        return account;
    }

    public void deposit(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public Account getAccount(long accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public void withdraw(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
    }

    public void depositEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.updateAccount(account);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public void withdrawEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.updateAccount(account);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void printAccountStatements() {
        // print statement printing time
        Date date = Calendar.getInstance().getTime();
        DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        System.out.println("Statement Time: " + timeFormatter.format(date));

        // print statements
        Collection<Account> accounts = this.getAllAccounts();
        Customer customer;
        for (Account account : accounts) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                        .toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------"
                    + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                    account.getBalance());
        }
    }
}

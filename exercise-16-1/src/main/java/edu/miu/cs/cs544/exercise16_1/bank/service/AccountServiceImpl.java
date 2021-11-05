package edu.miu.cs.cs544.exercise16_1.bank.service;

import edu.miu.cs.cs544.exercise16_1.bank.dao.AccountDAO;
import edu.miu.cs.cs544.exercise16_1.bank.dao.AccountDAOImpl;
import edu.miu.cs.cs544.exercise16_1.bank.dao.HibernateUtils;
import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise16_1.bank.domain.Customer;
import edu.miu.cs.cs544.exercise16_1.bank.jms.JMSSender;
import edu.miu.cs.cs544.exercise16_1.bank.jms.JMSSenderImpl;
import edu.miu.cs.cs544.exercise16_1.bank.logging.Logger;
import edu.miu.cs.cs544.exercise16_1.bank.logging.LoggerImpl;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;


public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final CurrencyConverter currencyConverter;
    private final JMSSender jmsSender;
    private final Logger logger;

    private final SessionFactory sf = HibernateUtils.getSessionFactory();

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
        currencyConverter = new CurrencyConverterImpl();
        jmsSender = new JMSSenderImpl();
        logger = new LoggerImpl();
    }

    public Account createAccount(long accountNumber, String customerName) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        tx.commit();
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
        return account;
    }

    public void deposit(long accountNumber, double amount) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);
        tx.commit();
        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public Account getAccount(long accountNumber) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = accountDAO.loadAccount(accountNumber);
        Hibernate.initialize(account.getEntryList());
        tx.commit();
        return account;
    }

    public Collection<Account> getAllAccounts() {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Collection<Account> accounts = accountDAO.getAccounts();
        accounts.forEach(acc -> Hibernate.initialize(acc.getEntryList()));
        tx.commit();
        return accounts;
    }

    public void withdraw(long accountNumber, double amount) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
        tx.commit();
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    public void depositEuros(long accountNumber, double amount) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.updateAccount(account);
        tx.commit();
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    public void withdrawEuros(long accountNumber, double amount) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.updateAccount(account);
        tx.commit();
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
        tx.commit();
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
    }
}

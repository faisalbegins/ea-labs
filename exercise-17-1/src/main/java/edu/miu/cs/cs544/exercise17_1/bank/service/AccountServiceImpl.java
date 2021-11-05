package edu.miu.cs.cs544.exercise17_1.bank.service;

import edu.miu.cs.cs544.exercise17_1.bank.dao.AccountDAO;
import edu.miu.cs.cs544.exercise17_1.bank.domain.Account;
import edu.miu.cs.cs544.exercise17_1.bank.domain.Customer;
import edu.miu.cs.cs544.exercise17_1.bank.jms.JMSSender;
import edu.miu.cs.cs544.exercise17_1.bank.logging.Logger;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final CurrencyConverter currencyConverter;
    private final JMSSender jmsSender;
    private final Logger logger;

    public AccountServiceImpl(CurrencyConverter currencyConverter,
                              AccountDAO accountDAO,
                              JMSSender jmsSender,
                              Logger logger) {
        this.currencyConverter = currencyConverter;
        this.accountDAO = accountDAO;
        this.jmsSender = jmsSender;
        this.logger = logger;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.saveAccount(account);
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deposit(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.deposit(amount);
        accountDAO.updateAccount(account);
        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Account getAccount(long accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        Hibernate.initialize(account.getEntryList());
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Collection<Account> getAllAccounts() {
        Collection<Account> accounts = accountDAO.getAccounts();
        accounts.forEach(acc -> Hibernate.initialize(acc.getEntryList()));
        return accounts;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void withdraw(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void depositEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void withdrawEuros(long accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountDAO.updateAccount(account);
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
        }
    }
}

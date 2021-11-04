package edu.miu.cs.cs544.exercise13_2.bank.dao;

import edu.miu.cs.cs544.exercise13_2.bank.domain.Account;

import java.util.Collection;


public interface IAccountDAO {
    void saveAccount(Account account);

    void updateAccount(Account account);

    Account loadAccount(long accountnumber);

    Collection<Account> getAccounts();
}

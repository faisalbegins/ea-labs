package edu.miu.cs.cs544.exercise16_1.bank.dao;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;

import java.util.Collection;


public interface AccountDAO {
    void saveAccount(Account account);

    void updateAccount(Account account);

    Account loadAccount(long accountnumber);

    Collection<Account> getAccounts();
}

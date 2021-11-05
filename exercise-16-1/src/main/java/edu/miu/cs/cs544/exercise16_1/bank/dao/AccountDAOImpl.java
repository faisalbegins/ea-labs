package edu.miu.cs.cs544.exercise16_1.bank.dao;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;
import org.hibernate.SessionFactory;

import java.util.Collection;

public class AccountDAOImpl implements AccountDAO {
    SessionFactory sf = HibernateUtils.getSessionFactory();

    public void saveAccount(Account account) {
        sf.getCurrentSession().persist(account);
    }

    public void updateAccount(Account account) {
        sf.getCurrentSession().merge(account);
    }

    public Account loadAccount(long accountNumber) {
        return sf.getCurrentSession().load(Account.class, accountNumber);
    }

    public Collection<Account> getAccounts() {
        return sf.getCurrentSession().createQuery("from Account", Account.class).list();
    }
}

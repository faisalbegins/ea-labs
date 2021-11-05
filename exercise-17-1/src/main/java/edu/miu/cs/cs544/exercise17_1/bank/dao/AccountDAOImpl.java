package edu.miu.cs.cs544.exercise17_1.bank.dao;

import edu.miu.cs.cs544.exercise17_1.bank.domain.Account;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class AccountDAOImpl implements AccountDAO {
    private final SessionFactory sf;

    public AccountDAOImpl(SessionFactory sf) {
        this.sf = sf;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAccount(Account account) {
        sf.getCurrentSession().persist(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAccount(Account account) {
        sf.getCurrentSession().merge(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Account loadAccount(long accountNumber) {
        return sf.getCurrentSession().load(Account.class, accountNumber);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Collection<Account> getAccounts() {
        return sf.getCurrentSession().createQuery("from Account", Account.class).list();
    }
}

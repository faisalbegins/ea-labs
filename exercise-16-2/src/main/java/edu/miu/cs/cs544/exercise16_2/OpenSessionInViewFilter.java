package edu.miu.cs.cs544.exercise16_2;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter implementation class OpenSessionInViewFilter
 */
public class OpenSessionInViewFilter implements Filter {
    private SessionFactory sf;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Transaction tx = sf.getCurrentSession().beginTransaction();
        try {
            System.out.println("receiving request");
            chain.doFilter(request, response);
            System.out.println("sending response");
            tx.commit();
        } catch (RuntimeException ex) {
            try {
                tx.rollback();
                ex.printStackTrace();
            } catch (RuntimeException rbEx) {
                System.out.println("Could not rollback transaction " + rbEx);
                rbEx.printStackTrace();
            }
        }
    }

    public void destroy() {
        sf.close();
    }

    public void init(FilterConfig arg0) throws ServletException {
        this.sf = HibernateUtils.getSessionFactory();
    }
}

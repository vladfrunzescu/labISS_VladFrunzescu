package persistence;

import domain.Sef;
import domain.Sef;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RepositorySef implements IRepositorySef {
    private final SessionFactory sessionFactory;

    public RepositorySef(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    public Sef login(String username, String parola){
        Sef sef = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    sef = (Sef) session.createQuery("from Sef where username = :u and parola = :p")
                            .setParameter("u", username)
                            .setParameter("p", parola)
                            .list().get(0);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        } catch (Exception e) {
            System.err.println("Exception " + e);
            e.printStackTrace();
        } finally {
            //sessionFactory.close();
        }
        return sef;
    }
}

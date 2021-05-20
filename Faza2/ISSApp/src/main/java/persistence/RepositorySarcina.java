package persistence;

import domain.Angajat;
import domain.Sarcina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RepositorySarcina implements IRepositorySarcina{

    private final SessionFactory sessionFactory;

    public RepositorySarcina(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }


    public Sarcina save(Sarcina sarcina){
        return null;
    }

    public Sarcina update(Sarcina sarcina){
        return null;
    }

    public Sarcina delete(Sarcina sarcina){
        return null;
    }

    public List<Sarcina> filterByAngajat(Long idAngajat){
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    List<Sarcina> sarcini = session.createQuery("from Sarcina where idAngajat = :idAngajat ", Sarcina.class)
                            .setParameter("idAngajat", idAngajat)
                            .list();
                    tx.commit();
                    return sarcini;
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
        return null;
    }
}

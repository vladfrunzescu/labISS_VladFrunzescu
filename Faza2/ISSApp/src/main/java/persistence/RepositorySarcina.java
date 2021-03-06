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


    public Sarcina save(Sarcina entity){
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.save(entity);
                    tx.commit();
                    return entity;
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

    public Sarcina update(Sarcina entity){
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.update(entity);
                    tx.commit();
                    return entity;
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

    public Sarcina delete(Sarcina entity){
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.delete(entity);
                    tx.commit();
                    return entity;
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

    public List<Sarcina> filterByAngajat(Long idAngajat){
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    List<Sarcina> sarcini = session.createQuery("from Sarcina where idAngajat = :idAngajat and status not like 'FINALIZATA'", Sarcina.class)
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

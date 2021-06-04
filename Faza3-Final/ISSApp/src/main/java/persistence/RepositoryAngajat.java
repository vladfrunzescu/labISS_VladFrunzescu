package persistence;

import domain.Angajat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAngajat implements IRepositoryAngajat {

    private final SessionFactory sessionFactory;

    public RepositoryAngajat(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    public Angajat findOne(Long id) {
        Angajat angajat = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    angajat = (Angajat) session.createQuery("from Angajat where id = :id")
                            .setParameter("id", id)
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
        return angajat;
    }

    public List<Angajat> filterByPresence() {
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    List<Angajat> angajati = session.createQuery("from Angajat where oraConectare is not null and oraDeconectare like 'null'", Angajat.class).list();
                    tx.commit();
                    return angajati;
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

    public Angajat login(String username, String parola) {
        Angajat angajat = null;
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;

                try {
                    tx = session.beginTransaction();
                    angajat = (Angajat) session.createQuery("from Angajat where username = :u and parola = :p")
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
           // sessionFactory.close();
        }
        return angajat;
    }

    public Angajat update(Angajat entity) {
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
}



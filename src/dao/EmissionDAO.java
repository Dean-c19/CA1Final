package dao;
import entities.Emission;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

// handle all database operations for the emissions
public class EmissionDAO {

    protected static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpaPU");

    public EmissionDAO() {

    }

    public void persist(Emission emission) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(emission);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(Emission emission) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(emission));
        em.getTransaction().commit();
        em.close();
    }

    public Emission getById(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Emission e = em.find(Emission.class, id);
        em.close();
        return e;
    }

    public List<Emission> getAllEmissions() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Emission> emissions = em.createNamedQuery("Emission.findAll", Emission.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return emissions;
    }
    public void update(Emission emission) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(emission);
        em.getTransaction().commit();
        em.close();
    }

    public List<Emission> getByCategory(String category) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Emission> emissions = em
                .createNamedQuery("Emission.findByCategory", Emission.class)
                .setParameter("category", category)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        return emissions;
    }

}
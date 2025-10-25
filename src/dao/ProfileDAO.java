package dao;

import entities.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProfileDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public ProfileDAO() {
		
	}
	
	public void persist(Profile profile) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(profile);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Profile profile) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(profile));
		em.getTransaction().commit();
		em.close();
	}
	
	public Profile merge(Profile profile) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Profile updatedProfile = em.merge(profile);
		em.getTransaction().commit();
		em.close();
		return updatedProfile;
	}
	
	


}

package dao;

import entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProfileDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public ProfileDAO() {
		
	}
	
	public void persist(Member member) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(member);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Member member) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(member));
		em.getTransaction().commit();
		em.close();
	}
	
	public Member merge(Member member) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Member updatedMember = em.merge(member);
		em.getTransaction().commit();
		em.close();
		return updatedMember;
	}
	
	


}

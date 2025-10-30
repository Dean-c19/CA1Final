package dao;

import entities.MembersPlan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommentDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public CommentDAO() {
		
	}
	
	public void persist(MembersPlan membersPlan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(membersPlan);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(MembersPlan membersPlan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(membersPlan));
		em.getTransaction().commit();
		em.close();
	}
	
	public MembersPlan merge(MembersPlan membersPlan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		MembersPlan updatedMembersPlan = em.merge(membersPlan);
		em.getTransaction().commit();
		em.close();
		return updatedMembersPlan;
	}
	
	


}

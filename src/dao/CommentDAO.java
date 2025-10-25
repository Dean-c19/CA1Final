package dao;

import entities.Comment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommentDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public CommentDAO() {
		
	}
	
	public void persist(Comment comment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Comment comment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(comment));
		em.getTransaction().commit();
		em.close();
	}
	
	public Comment merge(Comment comment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Comment updatedComment = em.merge(comment);
		em.getTransaction().commit();
		em.close();
		return updatedComment;
	}
	
	


}

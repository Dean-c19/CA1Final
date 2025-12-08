package dao;

import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class UserDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	

	public UserDAO(){

	}

	
	public void persist(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(user));
		em.getTransaction().commit();
		em.close();
	}
	
	public User getById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User m = em.find(User.class, id);
		em.close();
		return m;
	}
	
	public List<User> getAllUsers(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return users;
	}


}

package dao;

import entities.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public SubscriberDAO() {
		
	}
	
	public void persist(Payment payment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(payment);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Payment payment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(payment));
		em.getTransaction().commit();
		em.close();
	}
	
	public Payment merge(Payment payment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Payment updatedPayment = em.merge(payment);
		em.getTransaction().commit();
		em.close();
		return updatedPayment;
	}
	
	
	public List<Payment> getAllSubscribers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Payment> subscribersFromDB = new ArrayList<Payment>();
		subscribersFromDB = em.createNamedQuery("Subscriber.findAll").getResultList();
		em.getTransaction().commit();
		em.close();
		return subscribersFromDB;
	}
	
	public Payment getSubscriberByUsername(String username){
		EntityManager em = emf.createEntityManager();
		List<Payment> payments = (List<Payment>)
				em.createNamedQuery("Subscriber.findByUsername").
				setParameter("username", username).getResultList();
		em.close();
		//Do whatever you want with the subscriber(s) with that username
		//Here we just return the first one
		Payment sub = new Payment();
		for(Payment s: payments) {
			sub = s;
		}
		return sub;
	}

}

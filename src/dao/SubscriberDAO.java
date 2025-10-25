package dao;

import entities.Subscriber;

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
	
	public void persist(Subscriber subscriber) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(subscriber);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Subscriber subscriber) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(subscriber));
		em.getTransaction().commit();
		em.close();
	}
	
	public Subscriber merge(Subscriber subscriber) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Subscriber updatedSubscriber = em.merge(subscriber);
		em.getTransaction().commit();
		em.close();
		return updatedSubscriber;
	}
	
	
	public List<Subscriber> getAllSubscribers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Subscriber> subscribersFromDB = new ArrayList<Subscriber>();
		subscribersFromDB = em.createNamedQuery("Subscriber.findAll").getResultList();
		em.getTransaction().commit();
		em.close();
		return subscribersFromDB;
	}
	
	public Subscriber getSubscriberByUsername(String username){
		EntityManager em = emf.createEntityManager();
		List<Subscriber> subscribers = (List<Subscriber>) 
				em.createNamedQuery("Subscriber.findByUsername").
				setParameter("username", username).getResultList();
		em.close();
		//Do whatever you want with the subscriber(s) with that username
		//Here we just return the first one
		Subscriber sub = new Subscriber();
		for(Subscriber s: subscribers) {
			sub = s;
		}
		return sub;
	}

}

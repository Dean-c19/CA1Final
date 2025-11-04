package dao;

import entities.Payment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PaymentDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	
	
	public PaymentDAO() {
		
	}
	// saves a new payment entity into th e db
	//when user makes a payment it is recorded
	public void persist(Payment payment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(payment);
		em.getTransaction().commit();
		em.close();
	}
	// return a list of all payment records in the db that uses a named query to fetch all payments
	public List<Payment> getAllPayments(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Payment> payments = em.createNamedQuery("Payment.findAll", Payment.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return payments;
	}
	
	


}

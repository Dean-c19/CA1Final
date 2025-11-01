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
	
	public void persist(Payment payment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(payment);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Payment> getAllPayments(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Payment> payments = em.createQuery("select p from Payment p", Payment.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return payments;
	}
	
	


}

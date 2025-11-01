package dao;

import entities.MembersPlan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class MembersPlanDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	

	public MembersPlanDAO() {

	}

	
	public void persist(MembersPlan plan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(plan);
		em.getTransaction().commit();
		em.close();
	}


	public List<MembersPlan> getAllPlans() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<MembersPlan> plans = em.createNamedQuery("MembersPlan.findAll", MembersPlan.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return plans;
	}
	


}

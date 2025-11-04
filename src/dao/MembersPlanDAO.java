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

	// save new membership plan in db
	public void persist(MembersPlan plan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(plan);
		em.getTransaction().commit();
		em.close();
	}

// retreives all plans using named query
	public List<MembersPlan> getAllPlans() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<MembersPlan> plans = em.createNamedQuery("MembersPlan.findAll", MembersPlan.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return plans;
	}

	//finds a plan by its id
	public MembersPlan getById(int id) {
		EntityManager em = emf.createEntityManager();
		MembersPlan plan = em.find(MembersPlan.class, id);
		em.close();
		return plan;

	}

}

package dao;

import entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class MemberDAO {
	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("jpaPU"); 	

	public MemberDAO(){

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
	
	public Member getById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Member m = em.find(Member.class, id);
		em.close();
		return m;
	}
	
	public List<Member> getAllMembers(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Member> members = em.createNamedQuery("Member.findAll", Member.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return members;
	}


}

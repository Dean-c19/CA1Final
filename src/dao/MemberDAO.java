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
	
	public Member merge(Member member) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Member updatedMember = em.merge(member);
		em.getTransaction().commit();
		em.close();
		return updatedMember;
	}
	
	public List<Member> getAllMembers(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Member> members = em.createNamedQuery("Member.findAll", Member.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return members;
	}

	public Member getMemberByName(String name){
		EntityManager em = emf.createEntityManager();
		List<Member> members = em.createNamedQuery("Member.findByName", Member.class)
				.setParameter("name", name)
				.getResultList();
		em.close();
		return members.isEmpty() ? null : members.get(0);

	}


}

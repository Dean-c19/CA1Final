package entities;

import javax.persistence.*;

import java.util.List;

@NamedQueries({
		@NamedQuery(name = "Member.findAll", query = "select m from Member m ")
})


@Entity
public class Member {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String membersId;
	private String name;
	private String phone;
	private String address;
	private String goals;

	@ManyToOne// many members can be on the same plan
	private MembersPlan membersPlan;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Payment> payments;

	public Member() {

	}

	
	public Member(String membersId, String name, String phone, String address, String goals) {
		this.membersId = membersId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.goals = goals;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMembersId() {
		return membersId;
	}



	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}



	public List<Payment> getPayments() {
		return payments;
	}



	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;

	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getGoals(){
		return goals;

	}

	public void setGoals(String goals){
		this.goals = goals;
	}
	public MembersPlan getMembersPlan() {
		return membersPlan;
	}
	public void setMembersPlan(MembersPlan membersPlan){
		this.membersPlan = membersPlan;
	}


}

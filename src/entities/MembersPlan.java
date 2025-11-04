package entities;

import javax.persistence.*;

@NamedQuery(name =  "MembersPlan.findAll", query = "select p from MembersPlan p")
@Entity
public class MembersPlan {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String content;
	private double totalCost;

	public MembersPlan() {

	}

	public MembersPlan(String content, double totalCost) {
		this.content = content;
		this.totalCost = totalCost;

	}

	public int getId(){
		return id;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getTotalCost(){
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}

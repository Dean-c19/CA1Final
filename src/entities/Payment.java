package entities;

import javax.persistence.*;




@Entity
public class Payment {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String paymentDate;
	private double amount;
	


	public Payment() {

	}

	public Payment(String paymentDate, double amount) {
		this.paymentDate = paymentDate;
		this.amount= amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentDate(){
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}
	public Double getAmount(){
		return amount;
	}
	public void setAmount(double amount){
		this.amount =amount;

	}

}

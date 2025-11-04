package model;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "payment")
public class PaymentM {
    private String paymentDate;
    private double amount;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}


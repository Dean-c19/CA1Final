package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// supposed to to be used for transferring members data between postman and the server

@XmlRootElement(name = "member")
public class MemberM {

    private String membersId;
    private String name;
    private String phone;
    private String address;
    private String goals;
    private MembersPlanM plan;
    private List<PaymentM> payments;


    public String getMembersId() {
        return membersId;
    }

    public void setMembersId(String membersId) {
        this.membersId = membersId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public MembersPlanM getPlan() {
        return plan;
    }

    public void setPlan(MembersPlanM plan) {
        this.plan = plan;
    }

    public List<PaymentM> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentM> payments) {
        this.payments = payments;
    }
}

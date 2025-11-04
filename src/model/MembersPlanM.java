package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "membersPlan")
public class MembersPlanM {
    private int id;
    private String content;
    private double totalCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

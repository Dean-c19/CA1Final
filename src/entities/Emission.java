package entities;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



// Should contain the important data from xml / json files

@NamedQueries({
        @NamedQuery(name = "Emission.findAll", query = "select e from Emission e"),
        @NamedQuery(name = "Emission.findByCategory", query = "select e from Emission e where e.category = :category")
})

@XmlRootElement
@Entity
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String category;
    private String unit;
    private double predictedValue;
    private double actualValue;

    @OneToOne
    private User approvedBy;

    private boolean approved;

    public Emission() {
    }

    public Emission(String category, String unit, double predictedValue, double actualValue) {
        this.category = category;
        this.unit = unit;
        this.predictedValue = predictedValue;
        this.actualValue = actualValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public double getPredictedValue() {
        return predictedValue;
    }

    public void setPredictedValue(double predictedValue) {
        this.predictedValue = predictedValue;
    }


    public double getActualValue() {
        return actualValue;
    }

    public void setActualValue(double actualValue) {
        this.actualValue = actualValue;
    }
    @Override
    public String toString() {
        return "Emission " +
                "id=" + id +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", predictedValue=" + predictedValue +
                ", actualValue=" + actualValue +
                ", approved=" + approved +
                ", approvedBy=" + (approvedBy != null ? approvedBy.getName() : "null");
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}




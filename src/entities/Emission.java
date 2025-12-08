package entities;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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
}




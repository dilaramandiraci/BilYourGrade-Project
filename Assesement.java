/**
 * @author Dilara Mandıracı
 * edited by Işıl Özgü
 */
public class Assesement {
    private String name;
    private int weight;
    private double grade;

    public Assesement(String aName, int aweight)
    {
        this.name = aName;
        this.weight = aweight;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

     public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

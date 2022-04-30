/**
 * @author Dilara Mandıracı
 */
public class Assesement {
    private String name;
    private int weight;
    private int grade;

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

     public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

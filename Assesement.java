/**
 * @author Dilara Mandıracı
 *         edited by Işıl Özgü
 */
public class Assesement {
    private String name;
    private int weight;
    private double grade;

    /**
     * This constructor creates a Assesment with the given parameters
     * 
     * @param aName
     * @param aweight
     */
    public Assesement(String aName, int aweight) {
        this.name = aName;
        this.weight = aweight;
    }

    /**
     * Accesses to the name of the Assesment
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the Assesment
     * 
     * @param name is the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accesses to the weight of the assesment
     * 
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Modifies the weight
     * 
     * @param weight is the new weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Accesses to the grade that the user has from this Assesment
     * 
     * @return grade
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Modifies the grade of the user
     * 
     * @param grade is the new grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
}

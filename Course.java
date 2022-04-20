/**
 *  @author Dilara MANDIRACI
 */
import java.util.ArrayList;

public class Course {
    private ArrayList <Assesement> assesements; //Final %40, Lab %35 etc.
    private String name; //CS
    private String numericCode; //102

    public Course(String aName, ArrayList<Assesement> assesments, String numericCode)
    {
        this.name = aName;
        this.assesements = assesments;
        this.numericCode = numericCode;
    }

    public ArrayList<Assesement> getAssesements() {
        return assesements;
    }

    public void setAssesements(ArrayList<Assesement> assesements) {
        this.assesements = assesements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }
    
}

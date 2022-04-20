import java.util.ArrayList;

public class Course {
    private ArrayList <Assesement> assesements;
    private String name;

    public Course(String aName, ArrayList<Assesement> assesments)
    {
        this.name = aName;
        this.assesements = assesments;
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
    
    
}

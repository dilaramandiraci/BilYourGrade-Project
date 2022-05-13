/**
 * This class represents a Course object that is thought in Bilkent Univercity.
 *  @author Dilara MANDIRACI
 *  and edited by Isil OZGU
 */
import java.io.IOException;
import java.util.ArrayList;

public class Course{

    private ArrayList <Assesement> assesements= new ArrayList<Assesement>(); //Final %40, Lab %35 etc.//ArrayList was not created properly.
    private String name; //CS
    private String numericCode; //102

    /**
     * Constructs a course object with the name and a numeric code
     * @param aName
     * @param numericCode
     */
    public Course(String aName, String numericCode)
    {
        this.name = aName;
        this.numericCode = numericCode;
        try {
            assesements = getAssesementsFromCurriculum();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Constructs a course object from its full name
     * @param fullname
     */
    public Course(String fullname)
    {
        int a=fullname.length()-3;
        this.numericCode=fullname.substring(a);
        this.name=fullname.substring(0, a);
        try {
            assesements = getAssesementsFromCurriculum();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getFulName()
    {
        return this.name + numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }
/**
 * This method pull assesments from website by JSoup class
 * @return assesments 
 * @throws IOException
 */
    public ArrayList<Assesement> getAssesementsFromCurriculum() throws IOException
    {
        JSoup sylabbusscraper = new JSoup();
        return sylabbusscraper.scrapeSyllabuss(this);
    }

}

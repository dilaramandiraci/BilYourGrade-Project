/**
 * @author Dilara Mandıracı
 */
import java.io.IOException;
import java.util.ArrayList;

public class Person implements Changeable{
    private String userName;
    private String password;
    private String deptCode;
    private int year;
    private int semester;
    private int dataBaseId;
    private ArrayList <Course> courses;
    

    public Person(String aUserName, String aPassword, String aDeptCode, int aYearCode, int adataBaseId, int semesterCode)
    {
        this.userName = aUserName;
        this.password = aPassword;
        this.deptCode = aDeptCode;
        this.year = aYearCode;
        this.dataBaseId = adataBaseId;
        this.semester = semesterCode;
        try {
            this.courses = getDefaultCourses();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public Person(String aUserName, String aPassword, int adataBaseId)
    {
        this.userName = aUserName;
        this.password = aPassword;
        this.dataBaseId = adataBaseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getYearCode() {
        return year;
    }

    public void setYearCode(int yearCode) {
        this.year = yearCode;
    }

    public int getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(int dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public int getSemesterCode() {
        return semester;
    }

    public void setSemesterCode(int semesterCode) {
        this.semester = semesterCode;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
/**This method add the course in the parameter to ArrayList courses
 * @param aCourse you want to add
 */
    @Override
    public void addCourse(Course aCourse) {
       courses.add(aCourse);
        
    }
/**This method remove the course in the parameter from ArrayList courses
 * @param aCourse you want to drop
 */
    @Override
    public void dropCourse(Course aCourse) {
        courses.remove(aCourse);
        
    }
/**
 * This method get courses from website by using Jsoup class
 * @return Course ArrayList 
 * @throws IOException
 */
    public ArrayList<Course> getDefaultCourses() throws IOException
    {
        JSoup curriculumScraper = new JSoup();
        return curriculumScraper.scrapeCurriculum(this);
    }
    
}

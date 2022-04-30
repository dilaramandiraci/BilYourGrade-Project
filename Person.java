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
    

    public Person(String aUserName, String aPassword, String aDeptCode, int aYearCode, int dataBaseId, ArrayList<Course>courses, int semesterCode)
    {
        this.userName = aUserName;
        this.password = aPassword;
        this.deptCode = aDeptCode;
        this.year = aYearCode;
        this.dataBaseId = dataBaseId;
        this.courses = courses;
        this.semester = semesterCode;
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

    @Override
    public void addCourse(Course aCourse) {
       courses.add(aCourse);
        
    }

    @Override
    public void dropCourse(Course aCourse) {
        courses.remove(aCourse);
        
    }

    public ArrayList<Course> getDefaultCourses() throws IOException
    {
        JSoup curriculumScraper = new JSoup();
        return curriculumScraper.scrapeCurriculum(this);
    }
    
}

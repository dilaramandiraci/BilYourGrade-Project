import java.util.ArrayList;

public class Person {
    private String userName;
    private String password;
    private String deptCode;
    private int yearCode;
    private int semesterCode;
    private int dataBaseId;
    private ArrayList <Course> courses;
    

    public Person(String aUserName, String aPassword, String aDeptCode, int aYearCode, int dataBaseId, ArrayList<Course>courses, int semesterCode)
    {
        this.userName = aUserName;
        this.password = aPassword;
        this.deptCode = aDeptCode;
        this.yearCode = aYearCode;
        this.dataBaseId = dataBaseId;
        this.courses = courses;
        this.semesterCode = semesterCode;
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
        return yearCode;
    }

    public void setYearCode(int yearCode) {
        this.yearCode = yearCode;
    }

    public int getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(int dataBaseId) {
        this.dataBaseId = dataBaseId;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    
    
}

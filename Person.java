import java.util.ArrayList;

public class Person {
    private String userName;
    private String password;
    private String deptCode;
    private String yearCode;
    private int dataBaseId;
    private ArrayList <Course> courses;
    

    public Person(String aUserName, String aPassword, String aDeptCode, String aYearCode, int dataBaseId)
    {
        this.userName = aUserName;
        this.password = aPassword;
        this.deptCode = aDeptCode;
        this.yearCode = aYearCode;
        this.dataBaseId = dataBaseId;
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

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public int getDataBaseId() {
        return dataBaseId;
    }

    public void setDataBaseId(int dataBaseId) {
        this.dataBaseId = dataBaseId;
    }
    
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * Steven Byrne on youtube,modified by isilozgu
 */
public class Database {
    Connection con;

    public Database() throws Exception {
        con = getConnection();
    }

    public static void main(String[] args) throws Exception {

        // getConnection();
        // System.out.println(getDatabaseID());
        // System.out.println(getDeptNo());
        // System.out.println(userExists("Işıl", "8585"));
        Database mysql = new Database();
        // mysql.createUser("Barkın", "hello");
        // ArrayList<Integer> a = new ArrayList<>();
        // a.add(100);a.add(60);a.add(60);//a.add(80);a.add(85);a.add(90);a.add(75);a.add(80);
        // mysql.setScores(a, 1, "CS101");

        ArrayList<Integer> a = mysql.getBorders("MATH102");
        for (int element : a) {
            System.out.println(element);
        }
        // ArrayList<String> c = new ArrayList<>();
        // ArrayList<Integer> a = new ArrayList<>();
        // c.add("lab");
        // a.add(10);
        // c.add("homework");
        // a.add(20);
        // c.add("presentation");
        // a.add(20);
        // c.add("crying");
        // a.add(20);
        // c.add("stressing");
        // a.add(30);
        // mysql.setMethods4Courses(a, c, "MadeUpCourse");
    }

    public Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";// "com.mysql.jdbc.Driver"-->previously
            String url = "jdbc:mysql://localhost:3306/bilyourgrade";// "jdbc.mysql://localhost:3306/databasename" he has
                                                                    // the ip adress
            String username = "root";
            String password = "aboveba66A";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void createUser(Person aPerson) throws Exception {
        String username = aPerson.getUserName();
        String password = aPerson.getPassword();
        try {

            // Connection con = getConnection();
            PreparedStatement create = con.prepareStatement(
                    "INSERT INTO users (name, password) VALUES ( '" + username + "', '" + password + "')");

            create.executeUpdate();
        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        } finally {
            System.out.println("new user yeey!");
        }

    }

    public String getDeptNo(String deptcode) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con
                    .prepareStatement("SELECT number FROM codes WHERE dept_code = '" + deptcode + "'");
            String deptno = "";
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                deptno = result.getString("number");
            }
            System.out.println("Dept code selected succesfully!");
            if (deptno.length() == 1) {
                deptno = 0 + deptno;
            }

            return deptno;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public int getDatabaseID(String name, String password) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id FROM users WHERE name = '" + name + "' AND password = '" + password + "'");
            int databaseid = 0;
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                databaseid = Integer.parseInt(result.getString("id"));
            }
            System.out.println("Id selected succesfully!"+databaseid);
            return databaseid;

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int userExists(String name, String password) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT name, password FROM users ");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String aName = result.getString("name");
                String aPassword = result.getString("password");
                if (name.equals(aName) && password.equals(aPassword)) {
                    System.out.println("User exists!");
                    return getDatabaseID(name, password);
                }
            }
            System.out.println("This user does not exist!");

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void setScores(ArrayList<Double> a, int id, String courseName) {
        try {

            // Connection con = getConnection();
            // for (int i = 0; i < a.size(); i++)
            PreparedStatement create = con.prepareStatement(
                    "INSERT INTO scores (userid, courseName) VALUES ( '" + id + "', '"
                            + courseName + "')");

            create.executeUpdate();
            updateScores(a, id, courseName);

        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        } finally {
            System.out.println("scores set!");
        }

    }

    public void updateScores(ArrayList<Double> a, int id, String courseName) {
        try {

            // Connection con = getConnection();
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != null) {
                    PreparedStatement create = con.prepareStatement(
                            "UPDATE scores SET method" + (i + 1) + "grade = " + a.get(i) +
                                    " WHERE userid = " + id + " AND courseName = '" + courseName + "'");

                    create.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        } finally {
            System.out.println("scores updated!");
        }

    }

    public void setCourses4user(ArrayList<String> courseNames, int id) throws Exception {
        try {
            for (int i = 0; i < courseNames.size(); i++) {
                PreparedStatement statement = con
                        .prepareStatement("UPDATE users SET course" + (i + 1) + "name = '" + courseNames.get(i)
                                + "'   WHERE id = " + id);

                statement.executeUpdate();
            }

            System.out.println("Courses are set!");
        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        }

        // try
        // {
        // PreparedStatement statement = con.prepareStatement("SELECT dept_code FROM
        // users WHERE id = 3");

        // ResultSet result = statement.executeQuery();
        // while (result.next())
        // {
        // String bruh= result.getString("dept_code");//its literally nul
        // System.out.println(bruh);
        // System.out.println(bruh.equals(null));
        // }

        // }
        // catch(Exception e){System.out.println("smtin went wrong" + e);}

    }

    public void setAssessments4Course(ArrayList<Integer> mWeights, ArrayList<String> names, String courseName)
            throws Exception {
        try {
            PreparedStatement create = con.prepareStatement(
                    "INSERT INTO courses (courseName) VALUES ( '" + courseName + "')");
            create.executeUpdate();
            for (int i = 0; i < mWeights.size(); i++) {
                PreparedStatement statement = con
                        .prepareStatement("UPDATE courses SET method" + (i + 1) + "name = '" + names.get(i)
                                + "'   WHERE courseName = '" + courseName + "'");

                statement.executeUpdate();
                PreparedStatement up = con
                        .prepareStatement("UPDATE courses SET method" + (i + 1) + "weight = '" + mWeights.get(i)
                                + "'   WHERE courseName = '" + courseName + "'");

                up.executeUpdate();
            }

            System.out.println("methods are set!");
        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        }
    }

    /**
     * CAUTION SOME ELEMENTS OF THE ARRAYLIST MAY BE NULL
     * @param id
     * @param courseName
     * @return scores Double ArrayList
     */
    public ArrayList<Double> getScores(int id, String courseName) {
        try {
            ArrayList<Double> scores = new ArrayList<>();

            for (int i = 1; i < 9; i++) {
                PreparedStatement statement = con
                        .prepareStatement("SELECT method" + i + "grade FROM scores WHERE userid = " + id
                                + " AND courseName ='" + courseName + "'");

                ResultSet result = statement.executeQuery();
                result.next();

                if (result.getString("method" + i + "grade") != null) {
                    scores.add(Double.parseDouble(result.getString("method" + i + "grade")));

                } else {
                    scores.add(null);
                }
            }
            System.out.println("Accessed scores!");
            return scores;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public ArrayList<String> getCourses(int id) {
        try {
            ArrayList<String> courses = new ArrayList<>();

            for (int i = 1; i < 7; i++) {
                PreparedStatement statement = con
                        .prepareStatement("SELECT course" + i + "name FROM users WHERE id = " + id);

                ResultSet result = statement.executeQuery();
                result.next();

                if (result.getString("course" + i + "name") != null) {
                    courses.add(result.getString("course" + i + "name"));

                } else {
                    break;
                }
            }
            System.out.println("Accessed courses!");
            return courses;

        } catch (Exception e) {
            System.out.println("sıkıntı"+e);
        }
        return null;
    }

    public ArrayList<String> getMethodNames(String courseName) {
        try {
            ArrayList<String> methodNames = new ArrayList<>();

            for (int i = 1; i < 9; i++) {
                PreparedStatement statement = con
                        .prepareStatement(
                                "SELECT method" + i + "name FROM courses WHERE courseName = '" + courseName + "'");

                ResultSet result = statement.executeQuery();
                result.next();

                if (result.getString("method" + i + "name") != null) {
                    methodNames.add(result.getString("method" + i + "name"));

                } else {
                    break;
                }
            }
            System.out.println("Accessed method names!");
            return methodNames;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Integer> getMethodWeights(String courseName) {
        try {
            ArrayList<Integer> mw = new ArrayList<>();

            for (int i = 1; i < 9; i++) {
                PreparedStatement statement = con
                        .prepareStatement(
                                "SELECT method" + i + "weight FROM courses WHERE courseName = '" + courseName + "'");
                ResultSet result = statement.executeQuery();
                result.next();

                if (result.getString("method" + i + "weight") != null) {
                    mw.add(Integer.parseInt(result.getString("method" + i + "weight")));

                } else {
                    break;
                }
            }
            System.out.println("Accessed method weights!");
            return mw;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public ArrayList<Integer> getBorders(String courseName) {
        try {
            ArrayList<Integer> mw = new ArrayList<>();

            PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM lettergrade WHERE course_name = '" + courseName + "'");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                mw.add(Integer.parseInt(result.getString("F")));
                mw.add(Integer.parseInt(result.getString("D")));
                mw.add(Integer.parseInt(result.getString("D+")));
                mw.add(Integer.parseInt(result.getString("C-")));
                mw.add(Integer.parseInt(result.getString("C")));
                mw.add(Integer.parseInt(result.getString("C+")));
                mw.add(Integer.parseInt(result.getString("B-")));
                mw.add(Integer.parseInt(result.getString("B")));
                mw.add(Integer.parseInt(result.getString("B+")));
                mw.add(Integer.parseInt(result.getString("A-")));
                mw.add(Integer.parseInt(result.getString("A")));
            }

            System.out.println("Accessed borders!");
            return mw;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public void deleteCourse(int id, String courseName) {
        ArrayList<String> courses = getCourses(id);
        int index = courses.indexOf(courseName);
        courses.remove(index);
        try {
            PreparedStatement statement = con
                    .prepareStatement("UPDATE users SET course" + (index + 1) + "name = NULL WHERE id = " + id);

            statement.executeUpdate();
            PreparedStatement update = con
                    .prepareStatement(
                            "DELETE FROM scores WHERE userid = " + id + " AND courseName ='" + courseName + "'");

            update.executeUpdate();
            System.out.println("Course deleted!");
            setCourses4user(courses, id);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void AddCourse(int id, Course aCourse) {
        String courseName = aCourse.getName() + aCourse.getNumericCode();
        ArrayList<String> courses = getCourses(id);
        int index = courses.indexOf(courseName);
        if (index < 0 && courses.size()<6) {
            try {
                
                courses.add(courseName);
                setCourses4user(courses, id);

                PreparedStatement statement = con.prepareStatement("SELECT courseName FROM courses ");
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    if (result.getString("courseName").equals(courseName)) {
                        return;
                    }
                }
                ArrayList<Assesement> a = aCourse.getAssesements();
                ArrayList<Integer> weights = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();
                for (int i = 0; i < a.size(); i++) {
                    weights.add(a.get(i).getWeight());
                    names.add(a.get(i).getName());
                }
                setAssessments4Course(weights, names, courseName);

                System.out.println("Course added!");

            } catch (Exception e) {
                System.out.println("You cannot add this course!index"+index+"ww");
                System.out.println(e);
            }
        }
        

    }

}
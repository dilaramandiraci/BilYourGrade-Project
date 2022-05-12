import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    /**
     * It makes connection with MySQL and our database
     * @return
     * @throws Exception
     */
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
            System.out.println(e+"connection issue");
        }

        return null;
    }

    /**
     * This method saves a person to the database with their username and password
     * @param aPerson who will be saved to database
     * @throws Exception
     */
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

    /**
     * This method allows to use jsoup file to go to the departments curriculum page because their url numbers 
     * are not regular. So first we found them trivially and saved to our database.
     * @param deptcode returns dept code accordingly Bilkent Univerrity url
     * @return department no such as CS = 11
     * @throws Exception
     */
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
            System.out.println(e+"deptnoissue");
        }
        return null;

    }

    /**
     * This allow us to get users unique database id to access thier other information and utilize other methods with that id
     * @param name people name
     * @param password people password
     * @return their unique databaseId which is actually row and saving queue
     * @throws Exception
     */
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
            System.out.println(e+"databaseid issue");
        }
        return 0;
    }

    /**
     * When login tried, it checks if such a user exist. It matches names and password with database and login frame
     * @param name that given in login step
     * @param password that given in login step
     * @return if user exists return their database id, if not return 0
     */
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
            System.out.println(e+"userexistproblem");
        }
        return 0;
    }

    /**
     * It allows us to set grades to users so that they can access former scores after each login
     * @param a is a string arraylist which is actually grades in form like 10/15
     * @param id database id to access spesific persom
     * @param courseName setting scores of that course
     */
    public void setScores(ArrayList<String> a, int id, String courseName) {
        try {

            // Connection con = getConnection();
            // for (int i = 0; i < a.size(); i++)
            PreparedStatement create = con.prepareStatement(
                    "INSERT IGNORE INTO scores (userid, courseName) VALUES ( '" + id + "', '"
                            + courseName + "')");
                            System.out.println("insertignote setscores comin");

            create.executeUpdate();
            updateScores(a, id, courseName);

        } catch (Exception e) {
            System.out.println("smtin went wrong setscores " + e);
        } finally {
            System.out.println("scores set!");
        }

    }

    /**
     * This method allows you to update past scores
     * @param a grades
     * @param id unique database id to access person 
     * @param courseName uptading scores of that course
     */
    public void updateScores(ArrayList<String> a, int id, String courseName) {
        try {

            // Connection con = getConnection();
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) != null) {
                    PreparedStatement create = con.prepareStatement(
                            "UPDATE scores SET method" + (i + 1) + "grade = '" + a.get(i) +
                                    "' WHERE userid = " + id + " AND courseName = '" + courseName + "'");
                    System.out.println("updatescorescomin");
                    create.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.out.println("smtin went wrong updatescores" + e);
        } finally {
            System.out.println("scores updated!");
        }

    }
    /**
     * This method sets a dept code for the given user
     * @param id unique database id to access the user
     * @param deptcode CS, EE, IE ...
     */
    public void setDeptcode(int id, String deptcode)
    {
        PreparedStatement statement;
        try {
            statement = con
                            .prepareStatement("UPDATE users SET dept_code = '" + deptcode+ "'   WHERE id = " + id);
        
                            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deptcode problem");
            e.printStackTrace();
        }

    }
    /**
     * This method allows us to set courses for user
     * @param courseNames courses that will be set
     * @param id unique database id to access the user
     * @throws Exception
     */
    public void setCourses4user(ArrayList<String> courseNames, int id) throws Exception {
        try {
            PreparedStatement statement0 = con
                        .prepareStatement("UPDATE users SET course1name = NULL,course1name = NULL, course2name = NULL,course3name = NULL, "+
                        "course4name = NULL, course5name = NULL, course6name = NULL   WHERE id = " + id);

                statement0.executeUpdate();
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

    /**
     * This method allows us to set assesments for a course
     * @param mWeights weights will be set
     * @param names names of the assesments
     * @param courseName course name
     * @throws Exception
     */
    public void setAssessments4Course(ArrayList<Integer> mWeights, ArrayList<String> names, String courseName)
            throws Exception {
        try {
            PreparedStatement create = con.prepareStatement(
                    "INSERT IGNORE INTO courses (courseName) VALUES ( '" + courseName + "')");
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
     * @param id unique database id to access the user
     * @param courseName course name whose scores will be returned
     * @return scores Double ArrayList
     */
    public ArrayList<String> getScores(int id, String courseName) {
        try {
            ArrayList<String> scores = new ArrayList<>();

            for (int i = 1; i < 9; i++) {
                PreparedStatement statement = con
                        .prepareStatement("SELECT method" + i + "grade FROM scores WHERE userid = " + id
                                + " AND courseName ='" + courseName + "'");

                ResultSet result = statement.executeQuery();
                result.next();

                if (result.getString("method" + i + "grade") != null) {
                    scores.add(result.getString("method" + i + "grade"));

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

    /**
     * This method allow us to get person's current courses
     * @param id unique database id to access the user
     * @return current courses that is saved in database
     */
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

    /**
     * This method allows us to get assessment names for given course
     * @param courseName course name
     * @return assessments name for given course
     */
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

    /**
     * This method allows us to get method weights of the given course
     * @param courseName course name
     * @return given courses methods' weights
     */
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

    /**
     * This method bring informaiton for courses' letter grades borders such as 90-100 A
     * @param courseName course name
     * @return Integer arraylşst which includes top borders of the every letter grade for given course
     */
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

    /**
     * This method used for drop a coursse from given user
     * @param id unique database id to access the user
     * @param courseName course name
     */
    public void deleteCourse(int id, String courseName) {
        ArrayList<String> courses = getCourses(id);
        int index = courses.indexOf(courseName);System.out.println(courses.size());
        System.out.println(index+"index");
        courses.remove(index);
        System.out.println(courses.size());
        
        
        try {
             setCourses4user(courses, id);
            // PreparedStatement statement = con
            //         .prepareStatement("UPDATE users SET course" + (index + 1) + "name = NULL WHERE id = " + id);

             //statement.executeUpdate();
            PreparedStatement update = con
                    .prepareStatement(
                            "DELETE FROM scores WHERE userid = " + id + " AND courseName ='" + courseName + "'");

            update.executeUpdate();
            System.out.println("Course deleted!");
            

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * This method allow user to add a new course to their main menu and database
     * @param id unique database id to access the user
     * @param aCourse course name
     */
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
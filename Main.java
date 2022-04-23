import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 
 * Steven Byrne on youtube,modified by isilozgu
 */
public class Main {
    public static void main(String[] args) throws Exception {
        createUser("Işıl", "1234");
    }

    public static void createTable() throws Exception {
        try {

            Connection con = getConnection();// once we call this we are connected to the database?
            PreparedStatement create = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT,first varchar(255),last varchar(255),PRIMARY KEY(id))");

            create.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // this is how to connect to a database?
    public static Connection getConnection() throws Exception {
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

    public static void createUser(String username, String password) throws Exception {
        try {

            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement(
                    "INSERT INTO users (name, password) VALUES ( '" + username + "', '" + password + "')");

            create.executeUpdate();
        } catch (Exception e) {
            System.out.println("smtin went wrong" + e);
        } finally {
            System.out.println("new user yeey!");
        }

    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * Steven Byrne on youtube,modified by isilozgu
 */
public class Main {
    public static void main(String[] args) throws Exception {
       getConnection();

    }

    public static void createTable() throws Exception {
        try {

            Connection con = getConnection();
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
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/bilyourgrade";
                                                                    
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

    public static String getDeptNo() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT number FROM codes WHERE dept_code = 'CHEM'");
            String deptno="";
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                deptno= result.getString("number");
            }
            System.out.println("Dept code selected succesfully!");
            if(deptno.length()==1){ deptno=0+deptno;}

            return deptno;
            
        }catch(Exception e){System.out.println(e);}
        return null;
        
    }

    public static int getDatabaseID() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT id FROM users WHERE name = 'Işıl' AND password = '1234'");
            int databaseid=0;
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                databaseid= Integer.parseInt(result.getString("id"));
            }
            System.out.println("Id selected succesfully!");
            return databaseid;
            
        }catch(Exception e){System.out.println(e);}
        return 0;
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
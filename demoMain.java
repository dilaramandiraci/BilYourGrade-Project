import java.io.IOException;
import java.util.Arrays;

public class demoMain {
    public static void main(String[] args) throws IOException {
        Person person1 = new Person("Dilara", "12345", "CS", 1, 0, 2);
        
        System.out.println(person1.getCourses());
    }
    
}

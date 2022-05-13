/**
 * This interface is created so that Users cann change their courses. 
 * New classes such as UnderGrad od Grad may be added into the project in the 
 * future so this interface will serve its full purpose then.
 * @author Dilara Mandıracı 30.04.2022
 */
public interface Changeable {
    void addCourse(Course aCourse);
    void dropCourse(Course aCourse);
}

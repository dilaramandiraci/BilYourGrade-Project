

/*
 *İbrahim Barkın Çınar
*/

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CourseInner extends JFrame{
    

    //public static JFrame frame = new CourseInner();
    //VARIABLES
    Course course;
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 700;

    Random rand = new Random();

    ArrayList<JTextField> texts = new ArrayList<JTextField>();

    JButton backButton = new JButton();
    JLabel courseName = new JLabel();
    JPanel emptyPanel = new JPanel();

    //CONSTRUCTOR
    public CourseInner(Course aCourse, int dataBaseId){

        this.course = aCourse;
        setLocation(0, 0);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


        add(getBackButton());
        add(getCourseName());
        for(int i = 0; i < course.getAssesements().size() ; i++)//TO DO -> 5 yerine course un assesement sayısı gelicek
        {
            JLabel label = new JLabel();
            label.setText("assesement name " + course.getAssesements().get(i).getName()); // TO DO -> thiscourse.assesements[i].getName();
            label.setSize(300, 30);
            label.setLocation(30, 120 + (i*100));
            label.setBackground(new java.awt.Color(0,200,0));
            add(label);

            JTextField textField = new JTextField();
            
            textField.setSize(500, 60);
            textField.setLocation(40, 160 + (i*100));
            textField.setBackground(new java.awt.Color(0,200,0));
            add(textField);
        }

        add(getCalculateButton());
        add(emptyPanel);
        
    }
    
    public JButton getBackButton(){

        backButton.setText("<--");
        backButton.setSize(60, 60);
        backButton.setLocation(20,20);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        return backButton;
    }
    
    public JLabel getCourseName(){
        courseName.setText("TO DO (MATH102)");
        courseName.setSize(300, 60);
        courseName.setLocation(100,20);
        return courseName;
    }

    public JButton getCalculateButton()
    {
        JButton calculate = new JButton();
        
        calculate.setText("CALCULATE");
        calculate.setSize(200, 100);
        calculate.setLocation(FRAME_WIDTH-250, FRAME_HEIGHT-150);
        calculate.setBackground(new java.awt.Color(0,51,255));

        return calculate;
    }


    public static void main(String[] args) {
        
        /* CourseInner courseInner = new CourseInner();
        
        courseInner.setVisible(true);  
        courseInner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); */
    }
}

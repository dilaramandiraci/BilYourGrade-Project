

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
import javax.swing.Popup;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
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
    

    
    //VARIABLES
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;

    Random rand = new Random();


    JButton backButton = new JButton();
    JLabel courseName = new JLabel();

    //CONSTRUCTOR
    public CourseInner(){

        
        setLocation(0, 0);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


        add(getBackButton());
        add(getCourseName());
        for(int i = 0; i < 5 ; i++)//TO DO -> 5 yerine course un assesement sayısı gelicek
        {
            add(getAssesement(i));
        }
        

        JLabel label = new JLabel("only pain :/");

        add(label);
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

    public JPanel getAssesement(int i) {
        JPanel assesement = new JPanel();
        JLabel assesementName = new JLabel();
        assesementName.setText("assesement name " + i); // TO DO -> thiscourse.assesements[i].getName();
        assesementName.setSize(300, 60);
        assesementName.setLocation(20,100);
        assesement.setSize(500, 100);
        assesement.setLocation(20,120 + (i*100));
        assesement.setBackground(new java.awt.Color(rand.nextInt(200),rand.nextInt(200),rand.nextInt(200)));
        assesement.add(assesementName);



        return assesement;
    }


    public static void main(String[] args) {
        
        CourseInner courseInner = new CourseInner();
        
        courseInner.setVisible(true);  
        courseInner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

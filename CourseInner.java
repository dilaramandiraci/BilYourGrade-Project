
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


public class CourseInner extends JFrame implements ActionListener{
    

    //public static JFrame frame = new CourseInner();
    //VARIABLES
    Course course;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 700;

    Random rand = new Random();

    ArrayList<JTextField> texts = new ArrayList<JTextField>();
    ArrayList<Double> pastScores= new ArrayList<>(); 
    JButton backButton = new JButton();
    JLabel courseName = new JLabel();
    JPanel emptyPanel = new JPanel();
    JButton calculate = new JButton();
    Database dbase;
    int databaseId;
    int assesementCount;
    //mainMenu forOpening;
    

    //CONSTRUCTOR
    public CourseInner(Course aCourse, int AdataBaseId){

        try {
            dbase = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.course = aCourse;
        this.databaseId = AdataBaseId;
        assesementCount = dbase.getMethodNames(course.getFulName()).size();

        if(dbase.getScores(databaseId, course.getFulName())!=null)
        {pastScores = dbase.getScores(databaseId, course.getFulName());}

        setLocation(0, 0);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        add(getBackButton());
        add(getCourseName());

        for(int i = 0; i < assesementCount ; i++)//TO DO -> 5 yerine course un assesement sayısı gelicek
        {
            JLabel label = new JLabel();
            label.setText("" + dbase.getMethodNames(course.getFulName()).get(i).toUpperCase() + "  Weight %" + dbase.getMethodWeights(course.getFulName()).get(i)) ; // TO DO -> thiscourse.assesements[i].getName();
            label.setSize(300, 30);
            label.setLocation(30, 80 + (i*70));
            label.setForeground(Color.BLUE);
            add(label);

            if(pastScores.size()!=0)
            {
                if(pastScores.get(i)!=null)
                {
                    texts.add(new JTextField(""+pastScores.get(i)));
                }
                else
                {
                    texts.add(new JTextField());
                }
            }
            else
            {
                texts.add(new JTextField());
            }
            
            texts.get(i).setSize(300, 30);
            texts.get(i).setLocation(40, 110 + (i*70));
            add(texts.get(i));
        }
        

        add(getCalculateButton());
        add(emptyPanel);
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
    public JButton getBackButton(){

        backButton.addActionListener(this);
        backButton.setText("<--");
        backButton.setFont(new java.awt.Font("Arial Black", 0, 14));
        backButton.setSize(60, 30);
        backButton.setLocation(20,20);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        return backButton;
    }
    
    public JLabel getCourseName(){
        courseName.setText(course.getFulName());
        courseName.setFont(new java.awt.Font("Arial Black", 1, 18));
        courseName.setSize(300, 30);
        courseName.setLocation(100,20);
        return courseName;
    }

    public JButton getCalculateButton()
    {    
        calculate.addActionListener(this);;
        calculate.setText("CALCULATE");
        calculate.setForeground(Color.WHITE);
        calculate.setFont(new java.awt.Font("Arial Black", 0, 14));

        calculate.setSize(140, 60);
        calculate.setLocation((FRAME_WIDTH-140)/2, FRAME_HEIGHT-110);
        calculate.setBackground(new java.awt.Color(0,0,204));

        return calculate;
    }


    //LISTENERS
    
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == calculate)
        {
            double totalGrade = 0;
            double[] assesementGrades = new double[assesementCount];

            for(int i = 0; i < texts.size(); i++)
            {
                String assesmentString = texts.get(i).getText() ;
                double assesementGrade = 0;
                int slashCount = 0;
                int startingIndex = 0;
                double elmacık = 0;
                boolean isOpen = false;


                for(int j = 0; j< assesmentString.length(); j++)
                {

                    if(assesmentString.charAt(j) == '/')
                    {

                        slashCount++;
                        elmacık = Double.valueOf(assesmentString.substring(startingIndex, j));
                        isOpen = true;
                        startingIndex = j+1;
                    }
                    if(assesmentString.charAt(j) == '+')
                    {
                        elmacık = elmacık / Double.valueOf(assesmentString.substring(startingIndex, j));
                        isOpen = false;
                        assesementGrade += elmacık;
                        startingIndex = j+1;
                    }
                    if(j == assesmentString.length() -1 && isOpen && slashCount != 0)
                    {
                        elmacık = elmacık / Double.valueOf(assesmentString.substring(startingIndex));
                        assesementGrade += elmacık;
                    }
                }
                if(slashCount == 0){assesementGrade = 0;}
                else{assesementGrade = assesementGrade / slashCount;}
                
                assesementGrades[i] = assesementGrade;

                

            }
            ArrayList<Double> scores = new ArrayList<Double>();

            for(int j=0; j<assesementGrades.length; j++)
            {
                scores.add(assesementGrades[j]);
            }

            dbase.setScores(scores, databaseId, course.getFulName());
            
            for(int i = 0 ; i < assesementGrades.length ; i++)
            {
                totalGrade += assesementGrades[i] * dbase.getMethodWeights(course.getFulName()).get(i); 
            }

            calculateFrame calculateFrame = new calculateFrame(course, databaseId, totalGrade);
            calculateFrame.setVisible(true);
        }

        if(e.getSource() == backButton)
        {
           this.setVisible(false);
        }
    }

}

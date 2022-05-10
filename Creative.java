
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


public class Creative extends JFrame implements ActionListener{
    

    //public static JFrame frame = new CourseInner();
    //VARIABLES
    Course course;
    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 700;

    Random rand = new Random();

    ArrayList<JTextField> texts = new ArrayList<JTextField>();
    ArrayList<JTextField> weights = new ArrayList<JTextField>();


    JButton backButton = new JButton();
    JLabel courseName = new JLabel();
    JPanel emptyPanel = new JPanel();
    JButton calculate = new JButton();
    JButton plus = new JButton();
    JButton minus = new JButton();

    int assesementCount;
    

    //CONSTRUCTOR
    public Creative(int assesementCount){

        setLocation(0, 0);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("BilYourGrade");

        this.assesementCount = assesementCount;


        add(getCourseName());

        backButton.addActionListener(this);;
        backButton.setText("<--");
        backButton.setFont(new java.awt.Font("Arial Black", 0, 14));
        backButton.setSize(60, 30);
        backButton.setLocation(20,20);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        add(backButton);
        
        JLabel weightsLabel = new JLabel();
        weightsLabel.setText("Weights "); // TO DO -> thiscourse.assesements[i].getName();
        weightsLabel.setSize(300, 30);
        weightsLabel.setLocation(420, 80 );
        weightsLabel.setForeground(Color.BLUE);
        add(weightsLabel);

        
        plus.addActionListener(this);;
        plus.setText("+");
        plus.setForeground(Color.WHITE);
        plus.setFont(new java.awt.Font("Arial Black", 0, 23));
        plus.setSize(50, 50);
        plus.setLocation(100, FRAME_HEIGHT-110);
        plus.setBackground(new java.awt.Color(20,80,20));
        add(plus);

        minus.addActionListener(this);;
        minus.setText("-");
        minus.setForeground(Color.WHITE);
        minus.setFont(new java.awt.Font("Arial Black", 0, 23));
        minus.setSize(50, 50);
        minus.setLocation(10, FRAME_HEIGHT-110);
        minus.setBackground(new java.awt.Color(100,20,20));
        add(minus);

        for(int i = 0; i < assesementCount ; i++)//TO DO -> 5 yerine course un assesement sayısı gelicek
        {
            JLabel assesementName = new JLabel();
            assesementName.setText("Assesement " + (i+1)); // TO DO -> thiscourse.assesements[i].getName();
            assesementName.setSize(300, 30);
            assesementName.setLocation(30, 80 + (i*70));
            assesementName.setForeground(Color.BLUE);
            add(assesementName);


            texts.add(new JTextField());
            
            texts.get(i).setSize(300, 30);
            texts.get(i).setLocation(40, 110 + (i*70));
            add(texts.get(i));

            JLabel percentage = new JLabel();
            percentage.setText("% ");
            percentage.setFont(new java.awt.Font("Arial Black", 0, 14));
            percentage.setSize(300, 30);
            percentage.setLocation(400, 110 + (i*70));
            percentage.setForeground(Color.BLUE);
            add(percentage);

            weights.add(new JTextField());
            
            weights.get(i).setSize(30, 30);
            weights.get(i).setLocation(420, 110 + (i*70));
            add(weights.get(i));


        }
        

        add(getCalculateButton());
        add(emptyPanel);
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
    }
    
    
    public JLabel getCourseName(){
        courseName.setText("CREATIVE");
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


    //LISTENER
    
    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == calculate)
        {
            double totalGrade = 0;
            double[] assesementGrades = new double[assesementCount];

            for(int i = 0; i < texts.size(); i++)
            {
                String assesmentString = texts.get(i).getText() ;
                System.out.println(assesmentString);//** */
                double assesementGrade = 0;
                int slashCount = 0;
                boolean isOpen = false;
                int startingIndex = 0;
                double elmacık = 0;

                for(int j = 0; j< assesmentString.length(); j++)
                {
                    System.out.println("j =" + j + ", asses sting length="+ assesmentString.length());

                    if(assesmentString.charAt(j) == '/')
                    {
                        System.out.println("slash found");//*** */
                        slashCount++;
                        System.out.println("get" + assesmentString.substring(startingIndex, j));
                        elmacık = Double.valueOf(assesmentString.substring(startingIndex, j));
                        System.out.println("top elmacık = " + elmacık);
                        isOpen = true;
                        System.out.println("is open açıldı");
                        startingIndex = j+1;
                    }
                    if(assesmentString.charAt(j) == '+')
                    {
                        System.out.println("elmacık = " + elmacık + ", bölünecek to"+ Double.valueOf(assesmentString.substring(startingIndex, j)));
                        elmacık = elmacık / Double.valueOf(assesmentString.substring(startingIndex, j));
                        
                        isOpen = false;
                        System.out.println("is open kapandı");
                        assesementGrade += elmacık;
                        startingIndex = j+1;
                    }
                    if(j == assesmentString.length() -1 && isOpen && slashCount != 0)
                    {
                        System.out.println("end found");
                        System.out.println("elmacık şuan = " + elmacık);
                        System.out.println("elmacık bölünecek to " + Double.valueOf(assesmentString.substring(startingIndex)));
                        elmacık = elmacık / Double.valueOf(assesmentString.substring(startingIndex));
                        System.out.println(elmacık);
                        assesementGrade += elmacık;
                    }
                }

                if(slashCount == 0){assesementGrade = 0;}
                else{assesementGrade = assesementGrade / slashCount;}
                assesementGrades[i] = assesementGrade;

            }

            for(int i = 0 ; i < assesementGrades.length ; i++)
            {
                totalGrade += assesementGrades[i] * Double.valueOf(weights.get(i).getText()); 
            }

            JOptionPane.showMessageDialog(this,"Your Total Grade Is: " + totalGrade);


            

        }

        if(e.getSource() == plus)
        {
            this.setVisible(false);
            assesementCount++;
            Creative newCreative = new Creative(assesementCount);
            newCreative.setVisible(true);  
            newCreative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        }

        if(e.getSource() == minus)
        {
            this.setVisible(false);
            if(assesementCount>1){
            assesementCount--;}
            Creative newCreative = new Creative(assesementCount);
            newCreative.setVisible(true);  
            newCreative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        }
        
        if(e.getSource() == backButton)
        {
            System.out.println("ana");
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {

        Creative creative = new Creative(2);
        
        creative.setVisible(true);  
        creative.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

}

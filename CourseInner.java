

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
import java.awt.Color;
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

    JButton backButton = new JButton();

    //CONSTRUCTOR
    public CourseInner(){

        
        setLocation(0, 0);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


        add(getBackButton());
        JLabel label = new JLabel("only pain :/");
        add(label);
    }
    
    public JButton getBackButton(){
        backButton.setText("<--");
        backButton.setSize(60, 60);
        backButton.setLocation(30,30);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        return backButton;
    }

    public static void main(String[] args) {
        
        CourseInner courseInner = new CourseInner();
        
        courseInner.setVisible(true);  
        courseInner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

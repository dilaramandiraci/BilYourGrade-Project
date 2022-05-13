import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @authors Damla İmre & Dilara Mandıracı
 */

public class mainMenu extends JFrame {

    /**
     * Creates new form mainMenu
     */
    int frameHeight = 600;
    int frameWeight = 400;
    static int courseTh;
    static boolean forChanges = false;
    private Person person;

    JButton creativeButton = new JButton();

    public Person getPerson() {
        return person;
    }

    ArrayList<String> courses;
    JButton[] buttons;
    JButton[] courseButtons;
    private static int forActions = 0;

    public int getForActions() {
        return forActions;
    }

    public void setForActions(int forActions1) {
        forActions = forActions1;
    }

    public mainMenu(Person aPerson) throws Exception {
        this.setTitle("BilYourGrade");
        person = aPerson;
        Database dbase = new Database();
        courses = dbase.getCourses(person.getDataBaseId());
        ArrayList<Course> c = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            c.add(new Course(courses.get(i)));
        }
        person.setCourses(c);
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    private void initComponents() {

        setSize(frameWeight, frameHeight);

        allPanel = new javax.swing.JPanel();
        upPanel = new javax.swing.JPanel();
        middlePanel = new javax.swing.JPanel();
        downPanel = new javax.swing.JPanel();
        downLeftPanel = new javax.swing.JPanel();
        downRightPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        labelInput = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        creativeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        creativeButton.setText("Creative");
        creativeButton.setForeground(Color.WHITE);
        creativeButton.setFont(new java.awt.Font("Arial Black", 0, 23));
        creativeButton.setSize(50, 50);
        creativeButton.setBackground(new java.awt.Color(51, 255, 54));
        add(creativeButton);

        jLabel1.setBackground(new java.awt.Color(255, 0, 51));
        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("   COURSES");
        jLabel1.setSize(300, 30);
        jLabel1.setLocation(40, 60);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 153));
        jLabel7.setText("ADD->");
        jLabel7.setSize(100, 30);
        jLabel7.setLocation(20, frameHeight - 30);

        jTextField1.setText("Course");
        jTextField1.setCaretColor(new java.awt.Color(102, 0, 153));
        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setSize(300, 30);
        jTextField1.setLocation(80, frameHeight - 30);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Rockwell", 3, 20)); // NOI18N
        jLabel8.setText("   Welcome  " + person.getUserName().toUpperCase());
        jLabel8.setSize(100, 30);
        jLabel8.setLocation(60, 100);

        labelInput.setText("Enter the coure as (CS 101)");
        labelInput.setSize(200, 30);
        labelInput.setLocation(40, frameHeight - 30);

        allPanel.setLayout(new GridLayout(3, 1));
        upPanel.setLayout(new GridLayout(2, 1));
        upPanel.add(jLabel8);
        upPanel.add(jLabel1);
        allPanel.add(upPanel);

        modifyMiddlepanel(middlePanel);

        allPanel.add(middlePanel, BorderLayout.CENTER);
        downPanel.setLayout(new GridLayout(1, 2));
        downLeftPanel.add(jLabel7);
        downLeftPanel.add(jTextField1);
        downLeftPanel.add(creativeButton);
        downPanel.add(downLeftPanel);
        downRightPanel.setLayout(new GridLayout(5, 1));
        downRightPanel.add(labelInput);
        downPanel.add(downRightPanel);
        allPanel.add(downPanel, BorderLayout.SOUTH);

        add(allPanel);
    }
/**
 * This method make the frame dynamic
 * @param aPanel
 */
    public void modifyMiddlepanel(JPanel aPanel) {
        Database dbase;
        try {
            dbase = new Database();
            courses = dbase.getCourses(person.getDataBaseId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = courses.size();
        buttons = new JButton[n];
        courseButtons = new JButton[n];

        for (int i = 0; i < n; i++) {
            courseTh = i;
            buttons[i] = new javax.swing.JButton();
            buttons[i].setBackground(new java.awt.Color(255, 0, 0));
            buttons[i].setText("X");
            buttons[i].setSize(30, 30);
            buttons[i].setLocation(200, i * 50);
            buttons[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            courseButtons[i] = new javax.swing.JButton();
            courseButtons[i].setBackground(new java.awt.Color(0, 0, 204));
            courseButtons[i].setForeground(new java.awt.Color(255, 255, 255));
            courseButtons[i].setText(courses.get(i));
            courseButtons[i].setSize(160, 30);
            courseButtons[i].setLocation(20, i * 50);
            courseButtons[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });
            aPanel.add(courseButtons[i]);
            aPanel.add(buttons[i]);

        }
    }
/**
 * This method return parameter s to parameters of Course object and add the course to person and database
 * @param s
 */
    public void takeAddCourse(String s) {
        String courseNAME = "";
        String numericCode = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                courseNAME = s.substring(0, i);
                numericCode = s.substring(i + 1);

            }
        }
        Course newCourse = new Course(courseNAME, numericCode);
        try {
            Database d = new Database();
            d.AddCourse(person.getDataBaseId(), newCourse);
            person.addCourse(newCourse);
        } catch (Exception e) {
            System.out.println("takeaddcourse");
            e.printStackTrace();
        }
    }
/**
 * this method add the course which user write
 * @param evt
 */
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        takeAddCourse(jTextField1.getText());
        reFreshFrame();
    }
/**
 * this method fresh the frame after adding or removing a course
 */
    public void reFreshFrame() {
        allPanel.remove(middlePanel);
        middlePanel = new JPanel();
        modifyMiddlepanel(middlePanel);
        allPanel.add(middlePanel, BorderLayout.CENTER);
        allPanel.remove(downPanel);
        allPanel.add(downPanel, BorderLayout.SOUTH);
        allPanel.validate();
        allPanel.repaint();
    }
/**
 * this method open the message frame
 * @param evt
 */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < buttons.length; i++) {
            if (evt.getSource().equals(buttons[i])) {
                setForActions(i);
                System.out.println(i + "kaçıncı ders");
            }
        }

        new messageFrame(person, this).setVisible(true);
    } 

/**
 * this method open creative frame
 * @param evt
 */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        Creative creativeFrame = new Creative(1);
        creativeFrame.setVisible(true);
        creativeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
/**
 * this method open the course which user click of courseinner frame 
 * @param evt
 */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                for (int i = 0; i < courseButtons.length; i++) {
                    if (evt.getSource() == courseButtons[i]) {

                        new CourseInner(person.getCourses().get(i), person.getDataBaseId());
                        System.out.println("broooooooooooooo");

                    }
                }
            }
        });
    }

    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labelInput;
    private javax.swing.JPanel allPanel;
    private javax.swing.JPanel upPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JPanel downPanel;
    private javax.swing.JPanel downLeftPanel;
    private javax.swing.JPanel downRightPanel;
    private javax.swing.JTextField jTextField1;

}

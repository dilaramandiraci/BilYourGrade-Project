import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

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
    //public static JFrame frame = new JFrame();
    int frameHeight=600;//+Person.getCourses().size()*60;
    int frameWeight=400;
    static int courseTh;
    static boolean forChanges=false;
    private Person person;
    public Person getPerson() {
        return person;
    }

    ArrayList<String> courses;
    JButton[] buttons;
    JButton[] courseButtons;
    private static int forActions=0;
    public int getForActions() {
        return forActions;
    }

    public void setForActions(int forActions1) {
        forActions = forActions1;
    }
    

    public mainMenu(Person aPerson) throws Exception {
        
        person = aPerson;
        Database dbase = new Database();
        courses = dbase.getCourses(person.getDataBaseId());
        ArrayList<Course> c=new ArrayList<>();
        for(int i=0;i<courses.size();i++)
        {
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

        

        jLabel1.setBackground(new java.awt.Color(255, 0, 51));
        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("COURSES");
        jLabel1.setSize(300, 30);
        jLabel1.setLocation(40, 60);
        

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 153));
        jLabel7.setText("ADD->");
        jLabel7.setSize(100, 30);
        jLabel7.setLocation(20, frameHeight-30);
        

        jTextField1.setText("Course");
        jTextField1.setCaretColor(new java.awt.Color(102, 0, 153));
        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setSize(300, 30);
        jTextField1.setLocation(80, frameHeight-30);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Viner Hand ITC", 0, 12)); // NOI18N
        jLabel8.setText("Welcome "+person.getUserName());
        jLabel8.setSize(100, 30);
        jLabel8.setLocation(5, 100);

        labelInput.setText("Enter the coure as (CS 101)");
        labelInput.setSize(200, 30);
        labelInput.setLocation(40, frameHeight-30);

        allPanel.setLayout(new GridLayout(3,1));
        upPanel.setLayout(new GridLayout(2,1));
        upPanel.add(jLabel8);
        upPanel.add(jLabel1);
        allPanel.add(upPanel);
        
        modifyMiddlepanel(middlePanel);

        
allPanel.add(middlePanel, BorderLayout.CENTER);
downPanel.setLayout(new GridLayout(1,2));
downLeftPanel.add(jLabel7);
downLeftPanel.add(jTextField1);
downPanel.add(downLeftPanel);
downRightPanel.setLayout(new GridLayout(5,1));
downRightPanel.add(labelInput);
downPanel.add(downRightPanel);
allPanel.add(downPanel,BorderLayout.SOUTH);




        

        //pack();
        add(allPanel);
    }// </editor-fold> 

    public void modifyMiddlepanel(JPanel aPanel)
    {   
        Database dbase;
        try {
            dbase = new Database();
            courses = dbase.getCourses(person.getDataBaseId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        int n=courses.size();//Person.getCourses().size();
         buttons=new JButton[n];
         courseButtons=new JButton[n];
        
        for(int i=0;i<n;i++)
        {
           courseTh=i;
           buttons[i]=new javax.swing.JButton();
           buttons[i].setBackground(new java.awt.Color(255, 0, 0));
           buttons[i].setText("X");
           buttons[i].setSize(30, 30);
           buttons[i].setLocation(200, i*50);
           buttons[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
             }
            });
            courseButtons[i]=new javax.swing.JButton();
            courseButtons[i].setBackground(new java.awt.Color(0, 0, 204));
            courseButtons[i].setForeground(new java.awt.Color(255, 255, 255));
            courseButtons[i].setText(courses.get(i));
            courseButtons[i].setSize(160, 30);
            courseButtons[i].setLocation(20, i*50);
            courseButtons[i].addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
             }
         });
         aPanel.add(courseButtons[i]);
         aPanel.add(buttons[i]);

        }
    }   
    public void takeAddCourse(String s){
        String courseNAME="";
        String numericCode="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)== ' '){
                courseNAME=s.substring(0, i);
                //numericCode=Integer.parseInt(s.substring(i+1));
                numericCode=s.substring(i+1);

            }
        }
         Course newCourse=new Course(courseNAME, numericCode);
         //person.addCourse(newCourse);personun constructordan dolayı course arraylisti boş
        try {
            Database d= new Database();
            d.AddCourse(person.getDataBaseId(), newCourse);
            //middlePanel.repaint();
            //initComponents();
        } catch (Exception e) {
            System.out.println("takeaddcourse");
            e.printStackTrace();
        }
    }                       

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        takeAddCourse(jTextField1.getText());
       
        reFreshFrame();
        /* allPanel.remove(middlePanel);
        JPanel newPanel = new JPanel();
        modifyMiddlepanel(newPanel);
        allPanel.add(newPanel, BorderLayout.SOUTH);
        allPanel.validate();
        allPanel.repaint(); */
        
        
    }      
    public void reFreshFrame(){
        allPanel.remove(middlePanel);
        JPanel newPanel = new JPanel();
        modifyMiddlepanel(newPanel);
        allPanel.add(newPanel, BorderLayout.CENTER);
        allPanel.validate();
        allPanel.repaint();
    }                                     

          
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
             
                for(int i=0;i<buttons.length;i++){
                    if(evt.getSource().equals(buttons[i])){
                        setForActions(i);
                        System.out.println(i+"kaçıncı ders");
                    }
                }
                //setVisible(false);
                new messageFrame(person, this).setVisible(true);} //Q
               // messageFrame.messageFrame.setVisible(true);
                
                                         

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
               // messageFrame.messageFrame.setVisible(true);
                
            }});
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                //messageFrame.messageFrame.setVisible(true);
                
            }});
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
               // messageFrame.messageFrame.setVisible(true);
                
            }});
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                //messageFrame.messageFrame.setVisible(true);
                
            }});
    }                                        

                
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                for(int i=0;i<courseButtons.length;i++){
                    if(evt.getSource()==courseButtons[i]){
                        new CourseInner(person.getCourses().get(i),person.getDataBaseId());
                        System.out.println("broooooooooooooo");
                        
                    }
                }
                
               setVisible(false);
                
            }});
    }                             

                                            
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
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
        //</editor-fold>
        //mainMenu frame=new mainMenu(person);

        /* Create and display the form */
        
                //frame.setVisible(true);
               // super.getFrame
           
    }

    // Variables declaration - do not modify                     
    
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
    // End of variables declaration                   
}
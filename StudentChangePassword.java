import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentChangePassword extends JFrame 
{
    private final JLabel myLabel1,myLabel2,myLabel3;
    private final JTextField textField1,textField2,textField3;
    private final JButton submit,cancel; 
    private final JPanel panel;
    private final JTextArea j;
    private final String userId;

    public StudentChangePassword(String userId,String password)
    {
        super("Change Password");
        this.setSize(800,600);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        this.userId=userId;

        panel = new JPanel();
        panel.setBackground(new Color(0,138,150));
        panel.setBounds(0,0,800,600);
        panel.setLayout(null);

        myLabel1=new JLabel("Old PassWord");
        myLabel1.setBounds(120,100,150,50);
        panel.add(myLabel1);

        myLabel2=new JLabel("New PassWord");
        myLabel2.setBounds(120,200,150,50);
        panel.add(myLabel2);

        myLabel3=new JLabel("Confirm PassWord");
        myLabel3.setBounds(120,300,150,50);
        panel.add(myLabel3);

        textField1=new JTextField();
        textField1.setBounds(250,100,350,50);
        panel.add(textField1);

        textField2=new JTextField();
        textField2.setBounds(250,200,350,50);
        panel.add(textField2);

        textField3=new JTextField();
        textField3.setBounds(250,300,350,50);
        panel.add(textField3);

        submit=new JButton("Submit");
        submit.setBackground( new Color(0,138,150));
        submit.setBounds(250,400,100,50);
        panel.add(submit);

        cancel=new JButton("Cancel");
        cancel.setBackground( new Color(0,138,150));
        cancel.setBounds(400,400,100,50);
        panel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                 cancelP();  
            } 
        });

        j=new JTextArea();
        j.setBackground(Color.WHITE);
        j.setBounds(5,5,770,540);
        j.setEditable(false);
        panel.add(j);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                 actionChangePass(userId);  
            }
        });

        this.add(panel);
    }

    public void actionChangePass(String uid)
    {	
        String password=textField3.getText();
        Connection con=null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT `id`,`password` FROM `student` WHERE id ="+'"'+uid+'"'; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");//load driver
            System.out.println("driver loaded");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
            System.out.println("connection done");//connection with database established
            st = con.createStatement();//create statement
            System.out.println("statement created");
            rs = st.executeQuery(query);//getting result
            System.out.println("results received");
            System.out.println(".");

            query = "UPDATE student SET `password`="+password+" where id="+'"'+this.userId+'"';
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(this,"Changeing PassWord Successfully"); 

        }
        catch(Exception e){System.out.println(e.getMessage());}
    }

    public void cancelP(){
       this.setVisible(false);
       new StudentSettings(userId);
    } 
}
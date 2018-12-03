import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentProfile extends JFrame 
{

	private JLabel myLabel1,myLabel2,myLabel3,myLabel4;
	private JButton back;
	private JPanel panel;
	private JTextArea j;
	private String userId;
	
	public StudentProfile(String userId,String name,String password)
	{
		super("Student Profile");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.userId=userId;
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
	    myLabel1 = new JLabel("PROFILE DETAILS");
		myLabel1.setBounds(330, 20, 600, 50);
		panel.add(myLabel1);
		
		myLabel2=new JLabel("Name :"+name);
		myLabel2.setBounds(200,80,300,50);
		panel.add(myLabel2);
		
		myLabel3=new JLabel("ID :"+userId);
		myLabel3.setBounds(200,150,200,50);
		panel.add(myLabel3);
		
		myLabel4=new JLabel("Password :"+password);
		myLabel4.setBounds(200,220,200,50);
		panel.add(myLabel4);
		
		back = new JButton("BACK");
		back.setBackground( new Color(0,138,150));
		back.setBounds(620, 480, 100, 50);
		panel.add(back);
		back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { 
                         backbut(userId);  
                    }
                });
                this.add(panel);  
	} 
        public void backbut(String x){
            this.setVisible(false);
            new StudentHomepage(x);  
        } 
}
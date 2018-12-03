import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentSettings extends JFrame implements ActionListener
{
	private JButton changePassword, changeinfo, back;
	private JPanel panel;
	private JTextArea j; 
	private String userId;
	Connection conn=SQLConnection.DbConnector();
        PreparedStatement pst=null;
        ResultSet rs=null;
	public StudentSettings(String userId)
	{
		super("Student Settings");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.userId=userId;
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
                JTextField t1= new JTextField("New Password");
		t1.setBackground( new Color(255,255,255));
		t1.setBounds(200, 100, 400, 50);
		panel.add(t1);
                
		JTextField t2= new JTextField("Again New Password");
		t2.setBackground( new Color(255,255,255));
		t2.setBounds(200, 200, 400, 50);
		panel.add(t2);
                
		changePassword = new JButton("CHANGE PASSWORD");
		changePassword.setBackground( new Color(0,138,150));
		changePassword.setBounds(200, 300, 400, 50);
		panel.add(changePassword);
		changePassword.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  
                        if(conn!=null)
                        {
                            if(t1.getText().equals(t2.getText()))
                            {
                                String sql="UPDATE student SET password = '"+t2.getText()+"' WHERE id ="+userId;
                                try {
                                    pst=conn.prepareStatement(sql);
                                    pst.execute();
                                    pst.close();
                                    JOptionPane.showMessageDialog(panel, "Change Successful");
                                } catch (SQLException ex) {
                                    System.err.print(sql+ex);
                                } 
                            }
                            else{
                                JOptionPane.showMessageDialog(panel, "Password Not Match");
                            }
                        }
                    }
                });
                
		changeinfo = new JButton("CHANGE INFORMATION");
		changeinfo.setBackground( new Color(0,138,150));
		changeinfo.setBounds(200, 325, 400, 100);
		//panel.add(changeinfo);
		
		back = new JButton("BACK");
		back.setBackground( new Color(0,138,150));
		back.setBounds(620, 480, 100, 50);
		panel.add(back); 
		
		changePassword.addActionListener(this);
		changeinfo.addActionListener(this);
		back.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent a)
	{	
		if (a.getSource()==changePassword)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;
			try
					{
						String query =  "SELECT `id`, `name` FROM `librarian` WHERE id ="+'"'+userId+'"';
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("driver loaded");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
						System.out.println("connection done");
						st = con.createStatement();
						System.out.println("statement created");
						rs = st.executeQuery(query);
						System.out.println("results received");
								
						while(rs.next())
						{
							String userId = rs.getString("id");
							ChangePassword ush = new ChangePassword(this.userId);
							this.setVisible(false);
							ush.setVisible(true);
						}
								
					}			
					catch(Exception ex)
					{
						System.out.println("Exception ");
					}
					finally
					{
						try
						{
							if(rs!=null)
								rs.close();

							if(st!=null)
								st.close();

							if(con!=null)
								con.close();
						}
						catch(Exception ex){}
					}
		}
		else if (a.getSource()==changeinfo)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;
			try
					{
						String query =  "SELECT `id`, `password` FROM `librarian` WHERE id ="+'"'+userId+'"';
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("driver loaded");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop1","root","");
						System.out.println("connection done");
						st = con.createStatement();
						System.out.println("statement created");
						rs = st.executeQuery(query);
						System.out.println("results received");
								
						while(rs.next())
						{
							String userId = rs.getString("id");
							ChangeInfo ush = new ChangeInfo(this.userId);
							this.setVisible(false);
							ush.setVisible(true);
						}
								
					}			
					catch(Exception ex)
					{
						System.out.println("Exception ");
					}
					finally
					{
						try
						{
							if(rs!=null)
								rs.close();

							if(st!=null)
								st.close();

							if(con!=null)
								con.close();
						}
						catch(Exception ex){}
					}
		}
		else if (a.getSource()==back)
		{
			this.setVisible(false);
			new StudentHomepage(userId);
		}
		else{}
	}
}
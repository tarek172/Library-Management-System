import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentHomepage extends JFrame implements ActionListener
{
	private JTextField myTextField1;
	private JButton search, profile, bookRequest, borrowHistory, financial, settings, logout;
	private JPanel panel;
	private JTextArea j;
	private String userId;
	
	public StudentHomepage(String userId)
	{ 
		super("Student Homepage");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.userId=userId;
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		myTextField1 = new JTextField();
		myTextField1.setBounds(170, 20, 370, 35);
		//panel.add(myTextField1);
		
		
		search = new JButton("SEARCH");
		search.setBackground( new Color(0,138,150));
		search.setBounds(540, 21, 100, 33);
		//panel.add(search);
		
		profile = new JButton("PROFILE");
		profile.setBackground( new Color(0,138,150));
		profile.setBounds(101, 105, 166, 180);
		panel.add(profile);
		
		bookRequest = new JButton("BOOK REQUEST");
		bookRequest.setBackground( new Color(0,138,150));
		bookRequest.setBounds(317, 105, 166, 180);
		panel.add(bookRequest);
		
		borrowHistory = new JButton("BORROW HISTORY");
		borrowHistory.setBackground( new Color(0,138,150));
		borrowHistory.setBounds(533, 105, 166, 180);
		panel.add(borrowHistory);
		
		financial = new JButton("FINANCIAL");
		financial.setBackground( new Color(0,138,150));
		financial.setBounds(101, 335, 166, 180);
		//panel.add(financial);
		
		settings = new JButton("SETTINGS");
		settings.setBackground( new Color(0,138,150));
		settings.setBounds(101, 335, 166, 180);
		panel.add(settings);
		
		logout = new JButton("LOG OUT");
		logout.setBackground( new Color(0,138,150));
		logout.setBounds(317, 335, 166, 180);
		panel.add(logout);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		profile.addActionListener(this);
		bookRequest.addActionListener(this);
		borrowHistory.addActionListener(this);
		financial.addActionListener(this);
		settings.addActionListener(this);
		logout.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent a)
	{	
	
		
		if(a.getSource()==profile)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;//to get row by row result from DB
				     
			try
			{
				String query = "select * from student";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("driver loaded");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abcd1234");
				System.out.println("connection done");
				st = con.createStatement();
				System.out.println("statement created");
				rs = st.executeQuery(query);
				System.out.println("results received");
						
				while(rs.next())
				{
					String userId = rs.getString("id");
					String na = rs.getString("name");
					String pass = rs.getString("password");
					StudentProfile ush = new StudentProfile(this.userId,na,pass);;
					this.setVisible(false);
					ush.setVisible(true);
				}			
			}
				catch(Exception ex)
				{
					System.out.println("Exception");
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
		
		else if (a.getSource()==bookRequest)
		{
                    StudentBookRequest ush = new StudentBookRequest(this.userId);
                    this.setVisible(false);
                    ush.setVisible(true);

		}
		
		else if (a.getSource()==borrowHistory)
		{
		    StudentHistory ush = new StudentHistory(userId);
                    this.setVisible(false);
                    ush.setVisible(true); 
		}
		else if (a.getSource()==financial)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;
			try
					{
						String query =  "SELECT `id`,`financial` FROM `student` WHERE id ="+'"'+userId+'"';
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
							String financial = rs.getString("financial");
							StudentFinancial ush = new StudentFinancial(this.userId,financial);
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
		else if (a.getSource()==settings)
		{ 
                    StudentSettings ush = new StudentSettings(this.userId);
                    this.setVisible(false);
                    ush.setVisible(true);
		}
		else if (a.getSource()==logout)
		{
			this.setVisible(false);
			new LibraryManagementSystem();
		}
		else{}
	}
}
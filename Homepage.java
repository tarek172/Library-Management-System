import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Homepage extends JFrame implements ActionListener
{
	private JTextField t;
	private JButton search,profile, manageBook, settings, manageStudent, manageBorrower, logout;
	private JPanel panel;
	private JTextArea j;
	private String userId;
	
	public Homepage(String userId)
	{
		super("Librarian Homepage");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.userId=userId;
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		t = new JTextField();
		t.setBounds(170, 20, 370, 35);
		//panel.add(t);
		
		
		search = new JButton("SEARCH");
		search.setBackground( new Color(0,138,150));
		search.setBounds(540, 21, 100, 33);
		//panel.add(search);
		
		profile = new JButton("PROFILE");
		profile.setBackground( new Color(0,138,150));
		profile.setBounds(101, 105, 166, 180);
		panel.add(profile);
		
		manageBook = new JButton("Manage Book");
		manageBook.setBackground( new Color(0,138,150));
		manageBook.setBounds(317, 105, 166, 180);
		panel.add(manageBook);
		
		settings = new JButton("SETTINGS");
		settings.setBackground( new Color(0,138,150));
		settings.setBounds(317, 335, 166, 180);
		panel.add(settings);
		
		manageStudent = new JButton("Manage Student ");
		manageStudent.setBackground( new Color(0,138,150));
		manageStudent.setBounds(101, 335, 166, 180);
		panel.add(manageStudent);
		
		manageBorrower = new JButton("Manage Borrower");
		manageBorrower.setBackground( new Color(0,138,150));
		manageBorrower.setBounds(533, 105, 166, 180);
		panel.add(manageBorrower);
		
		logout = new JButton("LOG OUT");
		logout.setBackground( new Color(0,138,150));
		logout.setBounds(533, 335, 166, 180);
		panel.add(logout);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		profile.addActionListener(this);
		manageBook.addActionListener(this);
		settings.addActionListener(this);
		manageStudent.addActionListener(this);
		manageBorrower.addActionListener(this);
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
				String query = "select * from librarian";
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
					Profile ush = new Profile(this.userId,na,pass);;
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
		else if (a.getSource()==manageBook)
		{
			this.setVisible(false);
			new ManageBook(userId);
		}
		else if (a.getSource()==manageBorrower)
		{
			this.setVisible(false);
			new ManageBorrower(userId);
		}
		else if (a.getSource()==manageStudent)
		{
			this.setVisible(false);
			new ManageStudent(userId);
		}
		else if (a.getSource()==settings)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;
			try
					{
						String query =  "select * from librarian";
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
							Settings ush = new Settings(this.userId);
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
		else if (a.getSource()==logout)
		{
			this.setVisible(false);
			new LibraryManagementSystem();
		}
		else{}
	}
}
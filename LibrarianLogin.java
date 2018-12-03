import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LibrarianLogin extends JFrame implements ActionListener
{
	private JLabel myLabel1,myLabel2;
	private JTextField textField1;
	private JPasswordField textField2;
	private JButton login,cancel; 
	private JPanel panel;
	private JTextArea j;
	private boolean flag;
	
	public LibrarianLogin()
	{
		super("Librarian Log In");
		this.setSize(800,600);
		this.setDefaultCloseOperation(LibrarianLogin.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		myLabel1=new JLabel("User Id");
		myLabel1.setBounds(200, 150,100,80); 
		panel.add(myLabel1);
		
		myLabel2=new JLabel("Password");
		myLabel2.setBounds(200, 280,100,80); 
		panel.add(myLabel2);
		
		textField1=new JTextField();
		textField1.setBounds(350, 150, 300, 80);
                textField1.setText("");
		panel.add(textField1);
		
		textField2=new JPasswordField();
		textField2.setBounds(350, 280, 300, 80);
                textField2.setText("");
		panel.add(textField2);
		
		login=new JButton("Log in");
		login.setBackground( new Color(0,138,150));
		login.setBounds(300,410,100,50);
		panel.add(login);
		
		cancel=new JButton("Cancel");
		cancel.setBackground( new Color(0,138,150));
		cancel.setBounds(430,410,100,50);
		panel.add(cancel);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		login.addActionListener(this);
        cancel.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent a)
	{	
		if(a.getSource()==login)
		{
			System.out.println("hello");
			flag=true;
			check();
		}
		
		else if (a.getSource()==cancel)
		{
			this.setVisible(false);
			new LibraryManagementSystem();
		}
		else{}
	}
	
	public void check()
	{
        String query = "select id,name,password from librarian";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abcd1234");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
					
			while(rs.next())
			{
                String userId = rs.getString("id");
                String password = rs.getString("password");
				
				
				if(userId.equals(textField1.getText()))
				{
					flag=false;
					if(password.equals(textField2.getText()))
					{
							Homepage ush = new Homepage(userId);
							this.setVisible(false);
							ush.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Invalid pass"); 
					}
				}			
			}
			if(flag){JOptionPane.showMessageDialog(this,"Invalid name"); }
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
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
}





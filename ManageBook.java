import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageBook extends JFrame implements ActionListener
{
	private JButton bookList,addBook,deleteBook,bookRequest,back; 
	private JPanel panel;
	private JTextArea j;
	private String userId;
	
	public ManageBook(String userId)
	{ 
		super("Manage Book");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		bookList=new JButton("Book List");
		bookList.setBackground( new Color(0,138,150));
		bookList.setBounds(101, 105, 166, 180);
		panel.add(bookList);
		
		addBook=new JButton("Add New Book");
		addBook.setBackground( new Color(0,138,150));
		addBook.setBounds(317, 105, 166, 180);
		panel.add(addBook);
		
		deleteBook=new JButton("Delete Book");
		deleteBook.setBackground( new Color(0,138,150));
		deleteBook.setBounds(533, 105, 166, 180);
		panel.add(deleteBook);
		
		bookRequest=new JButton("Book Request");
		bookRequest.setBackground( new Color(0,138,150));
		bookRequest.setBounds(101, 335, 166, 180);
                bookRequest.disable();
		//panel.add(bookRequest);
		
		back=new JButton("Back");
		back.setBackground( new Color(0,138,150));
		back.setBounds(620, 480, 100, 50);
		panel.add(back);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		bookList.addActionListener(this);
        addBook.addActionListener(this);
		deleteBook.addActionListener(this);
        bookRequest.addActionListener(this);
		back.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent a)
	{	
		if(a.getSource()==bookList)
		{
			Connection con=null;//for connection
			Statement st = null;//for query execution
			ResultSet rs = null;//to get row by row result from DB
				     
			try
			{
				String query = "select * from book";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("driver loaded");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abcd1234");
				System.out.println("connection done");
				st = con.createStatement();
				System.out.println("statement created");
				rs = st.executeQuery(query);
				System.out.println("results received");
				ArrayList<Book> bklist =new ArrayList<Book>();	
                                String data[][] = null;
				while(rs.next())
				{
                                    Book bk =new Book();
                                    bk.setId(rs.getString("id")); 
                                    bk.setBookname(rs.getString("BOOKNAME"));
                                    bk.setCatagory(rs.getString("CATAGORY")); 
                                    bklist.add(bk); 
				}
                                this.setVisible(false);
								BookList ush = new BookList(bklist,userId);
                                ush.setVisible(true);
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
						catch(SQLException ex){}
					}
				
		}
		
		else if (a.getSource()==addBook)
		{
			this.setVisible(false);
			new AddBook();
		}
		else if (a.getSource()==deleteBook)
		{
			this.setVisible(false);
			new DeleteBook(userId);
		}
		else if (a.getSource()==bookRequest)
		{
			this.setVisible(false);
			new BookRequest(userId);
		}
		else if (a.getSource()==back)
		{
			this.setVisible(false);
			new Homepage(userId);
		}
		else{}
	}
}
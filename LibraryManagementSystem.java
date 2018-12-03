import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;


public class LibraryManagementSystem extends JFrame implements ActionListener
{
	private JLabel myLabel1,myLabel2;
	private JTextField textField1,textField2;
	private JButton userLogin,librarianLogin; 
	private JPanel panel;
	private JTextArea j;

	public LibraryManagementSystem()
	{
		super("Library Management System");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		userLogin=new JButton("User Log in");
		userLogin.setBackground( new Color(0,138,150));
		userLogin.setBounds(200, 175, 400, 100);
		panel.add(userLogin);
		
		librarianLogin=new JButton("Librarian Log in");
		librarianLogin.setBackground( new Color(0,138,150));
		librarianLogin.setBounds(200, 325, 400, 100);
		panel.add(librarianLogin);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		librarianLogin.addActionListener(this);
        userLogin.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
		
	}
	
	public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==librarianLogin)
        {
            setVisible(false);
            new LibrarianLogin();
        }
        else if(a.getSource()==userLogin)
        {
            setVisible(false);
            new UserLogin();
        }
		else
		{}
    }
	
}
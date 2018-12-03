import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManageBorrower extends JFrame implements ActionListener
{

	private JButton borrowedBook, borrowList,returnBook, back;
	private JPanel panel;
	private JTextArea j;
	private String userId;
	
	public ManageBorrower(String userId)
	{
		super("Manage Borrower");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		borrowedBook = new JButton("Borrowed Book");
		borrowedBook.setBackground( new Color(0,138,150));
		borrowedBook.setBounds(200, 100, 400, 100);
		panel.add(borrowedBook);
		
		borrowList = new JButton("Borrower's List");
		borrowList.setBackground( new Color(0,138,150));
		borrowList.setBounds(200, 230, 400, 100);
		//panel.add(borrowList);
		
		returnBook = new JButton("Return Book");
		returnBook.setBackground( new Color(0,138,150));
		returnBook.setBounds(200, 230, 400, 100);
		panel.add(returnBook);
		
		back = new JButton("BACK");
		back.setBackground( new Color(0,138,150));
		back.setBounds(620, 480, 100, 50);
		panel.add(back);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		borrowedBook.addActionListener(this);
		borrowList.addActionListener(this);
		returnBook.addActionListener(this);
		back.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
		
		
		
	}
	public void actionPerformed(ActionEvent a)
	{	
		if (a.getSource()==borrowedBook)
		{
			this.setVisible(false);
			new BorrowedBook(userId);
		}
		else if (a.getSource()==borrowList)
		{
			this.setVisible(false);
			new BorrowerList(userId);
		}
		else if (a.getSource()==returnBook)
		{
			this.setVisible(false);
			new ReturnBook(userId);
		}
		else if (a.getSource()==back)
		{
			this.setVisible(false);
			new Homepage(userId);
		}
		else{}
	}
}
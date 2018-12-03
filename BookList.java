import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class BookList extends JFrame implements ActionListener
{
	private JLabel myLabel1,myLabel2,myLabel3,myLabel4;
	private JTextField textField1,textField2;
	private JButton back; 
	private JPanel panel;
	private JTextArea j;
	String userId;
	public BookList(ArrayList<Book> bk,String userId)
	{
		super("Book List");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
                this.setContentPane(panel); 
                
                DefaultTableModel model = new DefaultTableModel(); 
                JTable table = new JTable(model);
                table.setBounds(5, 10, 805, 450);
                model.addColumn("ID"); 
                model.addColumn("Book Name"); 
                model.addColumn("Book Catagory");
                
                model.addRow(new Object[]{"ID","Book Name","Book Catagory"});
                for(int stringIndex = 0; stringIndex < bk.size(); ++stringIndex ) {
                    Book bok =bk.get(stringIndex);
                    model.addRow(new Object[]{bok.getId(),bok.getBookname(),bok.getCatagory()});
                }
                panel.add(table); 
                setVisible(true); 
              
		
		back=new JButton("Back");
		back.setBackground( new Color(0,138,150));
		back.setBounds(620, 480, 100, 50);
		panel.add(back);
		 
		
                back.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
	} 
	public void actionPerformed(ActionEvent a)
	{	
		if (a.getSource()==back)
		{
			this.setVisible(false);
			new ManageBook(userId);
		}
		else{}
	}
	
}
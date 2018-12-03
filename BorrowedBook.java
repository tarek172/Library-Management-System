import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class BorrowedBook extends JFrame implements ActionListener
{
	private JLabel myLabel1,myLabel2;
	private JTextField textField1,textField2;
	private JButton back; 
	private JPanel panel;
	private JTextArea j;
	Connection conn=SQLConnection.DbConnector();
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st = null;
        String userId;
	public BorrowedBook(String userId)
	{
		super("Borrowed Book");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(); 
                JTable table = new JTable(model);
                table.setBounds(5, 10, 800, 450);
                model.addColumn("ID"); 
                model.addColumn("Book Name"); 
                model.addColumn("Book Catagory");
                model.addColumn("Datetime");
                model.addRow(new Object[]{"ID","Student Id","Book Id","Datetime"});
                if(conn!=null){ 
                    String sql="select *from BORROW_BOOK where RETURNIS=0";
                    try {
                        st = conn.createStatement();
                        rs = st.executeQuery(sql);  
                        while(rs.next()){
                            model.addRow(new Object[]{rs.getString("id"),rs.getString("S_ID"),rs.getString("B_ID"),rs.getString("BORROW_DATETIME")});
                        }
                    } catch (SQLException ex) {
                        System.err.print(sql+ex);
                    } 
                } 
                panel.add(table); 
                setVisible(true); 
		back=new JButton("Back");
		back.setBackground( new Color(0,138,150));
		back.setBounds(350,500,100,50);
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
			new ManageBorrower(userId);
		}
		else{}
	}
	
}
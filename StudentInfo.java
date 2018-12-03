import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class StudentInfo extends JFrame implements ActionListener
{
	private final JButton back; 
	private JPanel panel;
	private JTextArea j;
	Connection conn=SQLConnection.DbConnector();
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st = null;
        String userId;
	public StudentInfo(String userId)
	{
		super("Borrowed Book");
		this.setSize(800,600);
		this.setDefaultCloseOperation(StudentInfo.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(); 
                JTable table = new JTable(model);
                table.setBounds(5, 10, 800, 450);
                model.addColumn("ID"); 
                model.addColumn("Name"); 
                model.addColumn("Financial"); 
                model.addRow(new Object[]{"ID","Student Name","Financial"});
                if(conn!=null){ 
                    String sql="select *from student";
                    try {
                        st = conn.createStatement();
                        rs = st.executeQuery(sql);  
                        while(rs.next()){
                            model.addRow(new Object[]{rs.getString("id"),rs.getString("Name"),rs.getString("Financial")});
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
	
	
        @Override
	public void actionPerformed(ActionEvent a)
	{	
            if (a.getSource()==back)
            {
                this.setVisible(false);
                new ManageStudent(userId);
            }
            else{}
	}
	
}
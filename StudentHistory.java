import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class StudentHistory extends JFrame 
{
    private JLabel myLabel1,myLabel2,myLabel3;
    private JButton back;
    private JPanel panel;
    private JTextArea j;
    private String userId;
    Connection conn=SQLConnection.DbConnector();
    PreparedStatement pst=null;
    ResultSet rs=null;
    Statement st = null;
    public StudentHistory(String userId)
    {
        super("History");
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
            String sql="select *from BORROW_BOOK where S_ID='"+userId+"'";
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
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                 backbut(userId);  
            }
        });
        this.add(panel);     
    } 
    public void backbut(String x){
        this.setVisible(false);
        new StudentHomepage(x);  
    } 
}
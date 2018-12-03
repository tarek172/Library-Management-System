import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

public class StudentBookRequest extends JFrame 
{
	private JLabel myLabel1,myLabel2;
	private JTextField myTextField1;
	private JButton search, back;
	private JTextArea j;
	private JPanel panel;
	private String userId;
	Connection conn=SQLConnection.DbConnector();
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st = null;
	public StudentBookRequest(String userId)
	{
        super("Book Request For Student");
        this.setSize(800,600);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(0,138,150));
        panel.setBounds(0,0,800,600);
        panel.setLayout(null);
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model);
        table.setBounds(5, 10, 400, 450);
        model.addColumn("ID"); 
        model.addColumn("Book Name"); 
        model.addColumn("Book Catagory"); 
        model.addRow(new Object[]{"ID","Book Name","Book Catagory"});
        if(conn!=null){ 
            String sql="select *from BOOK";
            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);  
                while(rs.next()){
                    model.addRow(new Object[]{rs.getString("id"),rs.getString("BOOKNAME"),rs.getString("CATAGORY")});
                }
            } catch (SQLException ex) {
                System.err.print(ex);
            } 
        } 
        panel.add(table); 
        setVisible(true); 
        
        JTextField textField1=new JTextField();
        textField1.setBounds(450, 10, 210, 30);
        panel.add(textField1);
        
        JButton JButton11=new JButton("Borrow");
        JButton11.setBounds(450, 60, 210, 30);
        panel.add(JButton11);
        JButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                String bookid=textField1.getText();
                String st_id=userId;
                if(conn!=null)
                {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate localDate = LocalDate.now();
                    String sql="insert into BORROW_BOOK(S_ID,B_ID,BORROW_DATETIME,RETURNIS) values("+st_id+","+bookid+",'"+dtf.format(localDate)+"',0)";
                    try {
                        pst=conn.prepareStatement(sql);
                        pst.execute();
                        pst.close();
                        JOptionPane.showMessageDialog(panel, "Insert Successful");
                    } catch (SQLException ex) {
                        System.err.print(sql+ex);
                    } 
                }
            }
        });
        
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
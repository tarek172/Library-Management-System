import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBook extends JFrame implements ActionListener
{
	private JLabel myLabel1,myLabel2;
	private JTextField textField1,textField2;
	private JButton submit,cancel; 
	private JPanel panel;
	private JTextArea j;
        String userId;
        Connection conn=SQLConnection.DbConnector();
        PreparedStatement pst=null;
        ResultSet rs=null;
	public DeleteBook(String userId)
	{
		super("Librarian Log In");
		this.setSize(800,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,138,150));
		panel.setBounds(0,0,800,600);
		panel.setLayout(null);
		
		myLabel1=new JLabel("Id");
		myLabel1.setBounds(120,150,80,50);
		panel.add(myLabel1);
		
		textField1=new JTextField();
		textField1.setBounds(210,150,250,50); 
		panel.add(textField1);
		
		submit=new JButton("Submit");
		submit.setBackground( new Color(0,138,150));
		submit.setBounds(135,350,100,50);
		panel.add(submit);
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { 
                        String bookid=textField1.getText(); 
                        if(conn!=null)
                        {
                            String sql="DELETE FROM book where id="+bookid;
                            try {
                                pst=conn.prepareStatement(sql);
                                pst.execute();
                                pst.close();
                                textField1.setText("");
                                JOptionPane.showMessageDialog(panel, "Delete Successful");
                            } 
                            catch (SQLException ex) {
                                JOptionPane.showMessageDialog(panel, "Delete Failed");
                            } 
                        }
                    }
                });
		
		cancel=new JButton("Cancel");
		cancel.setBackground( new Color(0,138,150));
		cancel.setBounds(285,350,100,50);
		panel.add(cancel);
		
		j=new JTextArea();
		j.setBackground(Color.WHITE);
		j.setBounds(5,5,770,540);
		j.setEditable(false);
		panel.add(j);
		
		submit.addActionListener(this);
        cancel.addActionListener(this);
		
		this.setVisible(true);
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent a)
	{	
		if(a.getSource()==submit)
		{
				
		}
		
		else if (a.getSource()==cancel)
		{
			this.setVisible(false);
			new ManageBook(userId);
		}
		else{}
	}
	
}
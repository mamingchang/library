package Library;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.html.ImageView;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class login extends JFrame implements ActionListener{
	private JTextField[] text = {new JTextField(10),new JTextField(10)};
	private JPasswordField[] password = {new JPasswordField(10),new JPasswordField(10)};
	private JButton[] button= {new JButton("��¼"),new JButton("���"),new JButton("��¼"),new JButton("���")};
private JLabel[] label = {new JLabel("ѧ�ţ�",JLabel.CENTER),new JLabel("���룺",JLabel.CENTER),new JLabel("����ԱID��",JLabel.CENTER),new JLabel("���룺",JLabel.CENTER)};
private JTabbedPane select;
private JPanel pane1;
private JPanel pane2;
	public login()
	{
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane1.setLayout(new GridLayout(3,2,12,13));
		pane2.setLayout(new GridLayout(3,2,12,13));
		
		pane1.add(label[0]);
		pane1.add(text[0]);
		pane1.add(label[1]);
		pane1.add(password[0]);
		pane1.add(button[0]);
		pane1.add(button[1]);
		
		pane2.add(label[2]);
		pane2.add(text[1]);
		pane2.add(label[3]);
		pane2.add(password[1]);
		pane2.add(button[2]);
		pane2.add(button[3]);
		
		//for(int i = 0;i<2;i++){
			//button[i].setBounds(10+i*120, 230, 100, 25);
		//}
		
		button[0].addActionListener(this);
		button[0].setActionCommand("student");
		button[2].addActionListener(this);
		button[2].setActionCommand("administraor");
		
		button[1].addActionListener(this);
		button[3].addActionListener(this);
		button[1].setActionCommand("back1");
		button[3].setActionCommand("back2");
		
		select = new JTabbedPane();
		
		select.add("ѧ���û�", pane1);
		select.add("����Ա�û�", pane2);
		
		this.add(select);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize(); //�����Ļ�Ĵ�С
		this.setTitle("test");
		this.setSize(340, 270);
		this.setLocation(screenSize.width/4, screenSize.height/4);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e) 
{
	String accept = e.getActionCommand();
	if(e.getSource()==button[1]||e.getSource()==button[3])
		//��������չ���ʱҪ���� ����
	{
		this.text[0].setText("");
		this.password[0].setText("");
		this.text[1].setText("");
		this.password[1].setText("");
	}
	else if(e.getSource()==button[0])
		//��ѧ���û���¼ʱ�������û������͸���̨����
	{
		String find = "Select * from student where xuehao = '";
		find +=text[0].getText()+"'";
		find +="and password = '";
		find+= password[0].getText()+"'";
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 result = statement.executeQuery(find);
		if(result.next())
		{
			new student(result.getString(1),result.getString(8));
			this.dispose();
		}
		else if(text[0].getText()==""||password[0].getText()=="")
		{
			JOptionPane.showMessageDialog(this,"ѧ��ѧ�Ż����벻��Ϊ�գ�����","��Ϣ",
                    JOptionPane.INFORMATION_MESSAGE);
return;
		}
		else
		{
			JOptionPane.showMessageDialog(this,"δ�ҵ�����������","��Ϣ",
                    JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		}
		catch(Exception ex){ex.printStackTrace();}
		finally
		{
			if(connect!=null)
			{
				try {
					connect.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(statement!=null)
			{
				try {
					statement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(result!=null)
			{
				try {
					result.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	else
		//������Ա�û���¼ʱ�������û����ύ����̨����
	{
		String find = "Select * from administraor where id = '";
		find +=text[1].getText()+"'";
		find +="and password = '";
		find+= password[1].getText()+"'";
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 result = statement.executeQuery(find);
		if(result.next())
		{
			this.dispose();
			new administaror(result.getString(1),result.getString(2));
			
		}
		else if(text[0].getText()==""||password[0].getText()=="")
		{
			JOptionPane.showMessageDialog(this,"ѧ��ѧ�Ż����벻��Ϊ�գ�����","��Ϣ",
                    JOptionPane.INFORMATION_MESSAGE);
return;
		}
		else
		{
			JOptionPane.showMessageDialog(this,"δ�ҵ�����������","��Ϣ",
                    JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		}
		catch(Exception ex){ex.printStackTrace();}
		finally
		{
			if(connect!=null)
			{
				try {
					connect.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(statement!=null)
			{
				try {
					statement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(result!=null)
			{
				try {
					result.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
public static void main(String args[])
{
	Logo lg = new Logo("F:\\eclipse-workspace\\Library\\school_logo.jpg");
	Thread Lg = new Thread(lg);
	Lg.start();
	try {
		Lg.sleep(1200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	new login();//�Ӵ���login��ʼ������
	lg.setVisible(false);
}
}

package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class student_fine extends JPanel implements ActionListener
{
	//private JLabel jl=new JLabel("����������ѧ��");
	//private JTextField jtf=new JTextField();//�����ı���
	private JLabel jl1=new JLabel("��������Ҫ���Ŀ���");
	private JTextField jtf1=new JTextField();
	//������ť
	private JButton jb=new JButton("����");
	private JButton jb1=new JButton("��ѯǷ��");
	private String student_id = null;
	public student_fine(String new_id)
	{
		this.student_id = new_id;
		this.setLayout(null);//���ò���Ϊ�ղ���
		//this.add(jl);//��JLabel��ӽ�JPanel
		//this.add(jtf);//��JTextField��ӽ�JPanel
		this.add(jl1);
		this.add(jtf1);//��JTextField��ӽ�JPanel
		//��JButton��ӽ�JPanel
		this.add(jb);
		this.add(jb1);
		//�ֱ�����JLabel,JTextField,JButton�Ĵ�Сλ��
		//jl.setBounds(50,30,120,20);
		//jtf.setBounds(170,30,120,20);
		jl1.setBounds(50,70,120,20);
		jtf1.setBounds(170,70,120,20);
		jb.setBounds(180,110,100,25);
		//Ϊ��ť����¼�������
		jb.addActionListener(this);
		jb1.addActionListener(this);
		jb1.setBounds(50,110,100,25);
		//���ô���Ĵ�Сλ��
        this.setBounds(300,300,600,650);
        this.setVisible(true);
	}
	//Ϊ�¼����صļ��������ϴ����¼�
   	@SuppressWarnings("finally")
	public void actionPerformed(ActionEvent e)
	{
   		String answer  =null;
		if(e.getSource()==jb1)
		{//�¼�ԴΪ"��ѯǷ��"��ť
			String find = "Select money from fine where id = '"+this.student_id+"'";
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				result=statement.executeQuery(find);
				if(result.next())
				{
					answer = result.getString(1);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			finally
			{
				if(connect!=null)
				{
					try 
					{
						connect.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(statement!=null)
				{
					try 
					{
						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(result!=null)
				{
					try 
					{
						result.close();
					} catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(this,"����Ƿ��"+answer+"Ԫ",
						"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
                 return;
			}
		}
    	
		else if(e.getSource()==jb)
		{//�¼�ԴΪ"����"��ť
			int answer2 = 0;
			if(jtf1.getText().trim().equals(""))
			{//�ɷѽ��Ϊ�յ���ʾ
				JOptionPane.showMessageDialog(this,"������ɿ���(����)����",
										"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else
			{
				String find = "Select money from fine where id = '"+this.student_id+"'";
				Connection connect = null;
				Statement statement  = null;
				ResultSet result = null;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
					 statement = connect.createStatement();
					result=statement.executeQuery(find);
					if(result.next())
					{
						answer2 = result.getInt(1);
					}
					if(answer2<=(new Integer(this.jtf1.getText())).intValue())
					{
						statement.execute("update fine set money = money-"+answer2+" where id = '"+this.student_id+"'");
					}
					else
					{
						statement.execute("update fine set money = 0 where id = '"+this.student_id+"'");
					}
					result=statement.executeQuery(find);
					if(result.next())
					{
						answer2 = result.getInt(1);
					}
					if(answer2 == 0)
					{
						statement.execute("update student set permit = '��' where xuehao = '"+this.student_id+"'");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				finally
				{
					if(connect!=null)
					{
						try 
						{
							connect.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					if(statement!=null)
					{
						try 
						{
							statement.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					if(result!=null)
					{
						try 
						{
							result.close();
						} catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					JOptionPane.showMessageDialog(this,"����ɹ�������",
							"��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					this.jtf1.setText("");
	                 return;
				}
			}
		}
	}
}

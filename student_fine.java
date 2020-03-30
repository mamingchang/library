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
	//private JLabel jl=new JLabel("请输入您的学号");
	//private JTextField jtf=new JTextField();//创建文本框
	private JLabel jl1=new JLabel("请输入您要交的款数");
	private JTextField jtf1=new JTextField();
	//创建按钮
	private JButton jb=new JButton("交费");
	private JButton jb1=new JButton("查询欠费");
	private String student_id = null;
	public student_fine(String new_id)
	{
		this.student_id = new_id;
		this.setLayout(null);//设置布局为空布局
		//this.add(jl);//将JLabel添加进JPanel
		//this.add(jtf);//将JTextField添加进JPanel
		this.add(jl1);
		this.add(jtf1);//将JTextField添加进JPanel
		//将JButton添加进JPanel
		this.add(jb);
		this.add(jb1);
		//分别设置JLabel,JTextField,JButton的大小位置
		//jl.setBounds(50,30,120,20);
		//jtf.setBounds(170,30,120,20);
		jl1.setBounds(50,70,120,20);
		jtf1.setBounds(170,70,120,20);
		jb.setBounds(180,110,100,25);
		//为按钮添加事件监听器
		jb.addActionListener(this);
		jb1.addActionListener(this);
		jb1.setBounds(50,110,100,25);
		//设置窗体的大小位置
        this.setBounds(300,300,600,650);
        this.setVisible(true);
	}
	//为事件加载的监听器加上处理事件
   	@SuppressWarnings("finally")
	public void actionPerformed(ActionEvent e)
	{
   		String answer  =null;
		if(e.getSource()==jb1)
		{//事件源为"查询欠款"按钮
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
				JOptionPane.showMessageDialog(this,"您已欠费"+answer+"元",
						"消息",JOptionPane.INFORMATION_MESSAGE);
                 return;
			}
		}
    	
		else if(e.getSource()==jb)
		{//事件源为"交费"按钮
			int answer2 = 0;
			if(jtf1.getText().trim().equals(""))
			{//缴费金额为空的提示
				JOptionPane.showMessageDialog(this,"请输入缴款金额(数额)！！",
										"消息",JOptionPane.INFORMATION_MESSAGE);
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
						statement.execute("update student set permit = '是' where xuehao = '"+this.student_id+"'");
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
					JOptionPane.showMessageDialog(this,"还款成功！！！",
							"消息",JOptionPane.INFORMATION_MESSAGE);
					this.jtf1.setText("");
	                 return;
				}
			}
		}
	}
}

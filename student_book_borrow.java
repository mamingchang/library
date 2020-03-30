package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class student_book_borrow extends JPanel implements ActionListener
{
	//创建分割方向为上下的JSplitePane对象
    private JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jp2=new JPanel();
	//创建按钮数组
	int flag;
	String sql;
	DataBase db;
	private JButton jb2=new JButton("预约");
	private JButton jb3=new JButton("确定");
	private JButton jb4=new JButton("刷新");
	private JLabel jl3=new JLabel("请输入要查询的书名");
	//在jsp1添加文本框
	private JTextField jtxt3=new JTextField();
	private String student_id = null;
	Vector<String> head = new Vector<String>();	//创建标题
	{
		head.add("书号");
		head.add("书名");
		head.add("作者");
		head.add("出版社");
		head.add("出版时间");
		head.add("ISBN");
		head.add("库存");
	}
	//在jp3中设置表格
	Vector<Vector> data=new Vector<Vector>();
	//尝试性查询-开始		
	
		public Vector<Vector> first_data(Vector<Vector> data)
		{
			String sql = "";
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<String> accept = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				result = statement.executeQuery("select * from book_info");
				data = new Vector<Vector>();
				 while(result.next())
				 {
					 accept = new Vector<>();
					 accept.addElement(result.getString(1));
					 accept.addElement(result.getString(2));
					 accept.addElement(result.getString(3));
					 accept.addElement(result.getString(4));
					 accept.addElement(result.getString(5));
					 accept.addElement(result.getString(6));
					 accept.addElement(result.getString(7));
					 data.add(accept);
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
			return data;
		}
		
		DefaultTableModel dtm = null;
		JTable jt = null;
		JScrollPane jspn = null;
		
		
		
	//尝试性查询-结束
    public student_book_borrow(String id)
    {
    	this.student_id = id;
    	this.data = first_data(this.data);
    	 //创建表格模型
        this.dtm=new DefaultTableModel(data,head);
        //创建Jtable对象
    	this.jt=new JTable(dtm);
    	//将JTable封装到滚动窗格
    	this.jspn=new JScrollPane(jt);
    	this.setLayout(new GridLayout(1,1));
    	//把jsp2设置到jsp1的上部窗格
    	jsp1.setTopComponent(jp2);
    	//设置jsp1的下部窗格
    	jsp1.setBottomComponent(jspn);
    	//设置jsp1，jsp2中分割条的初始位置
    	jsp1.setDividerLocation(80);
    	//设置jsp1，jsp2中分割条的宽度
    	jsp1.setDividerSize(4);
    	//设置jp1，jp2为空布局管理器
    	jp2.setLayout(null);
    	//设置按钮的大小与位置
		jb2.setBounds(500,30,95,20);
		jb3.setBounds(280,30,95,20);
		jb4.setBounds(720, 30, 95, 20);
    	//将按钮添加进JPanel
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		//设置JLabel的坐标
    	jl3.setBounds(30,30,120,20);
    	//把JLabel添加进JPanel
    	jp2.add(jl3);
    	jtxt3.setBounds(155,30,100,20);
    	jp2.add(jtxt3);
    	this.add(jsp1);
    	//设置窗体的标题，大小位置及可见性
        this.setBounds(10,10,800,600);
        this.setVisible(true);  
    }	
    //为事件加载的监听器加上处理事件

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb2){
			boolean judge = false;
			int row = jt.getSelectedRow();
			String selected_row = jt.getValueAt(row,1).toString();
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			//Vector<Vector> vtemp = null;
			Vector<String> accept = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 result = statement.executeQuery("select quantity from book_info where name = '"+selected_row+"'");
		 int acc = 0;
		 while(result.next())
			{
			 acc = result.getInt(1);
				if(acc>0)
				{
					judge = true;
				}
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
	if(judge)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
			 statement = connect.createStatement();
			 statement.execute("update book_info set quantity = quantity-1 where name = '"+selected_row+"'");
			 long kk = (new Date().getTime())/86400000+30-17800;
			 long jj = (new Date().getTime())/86400000-17800;
			 if(statement.executeQuery("select * from record where id = '"+this.student_id+"' and book_name = '"+selected_row+"'").next())
			 {
				 JOptionPane.showMessageDialog(this,"您已借阅/预约该书，无法重复操作！！",
			                "消息!!",JOptionPane.INFORMATION_MESSAGE);
					return;
			 }
			 else
			 {
				 statement.execute("insert into record values('"+this.student_id+"','"+selected_row+"','"+jj+"','"+kk+"','是','否')");
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
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
			 statement = connect.createStatement();
			result = statement.executeQuery("select * from book_info");
			data = new Vector<Vector>();
			 while(result.next())
			 {
				 accept = new Vector<>();
				 accept.addElement(result.getString(1));
				 accept.addElement(result.getString(2));
				 accept.addElement(result.getString(3));
				 accept.addElement(result.getString(4));
				 accept.addElement(result.getString(5));
				 accept.addElement(result.getString(6));
				 accept.addElement(result.getString(7));
				 data.add(accept);
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
				dtm.setDataVector(data,head);//更新table并显示	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"借阅成功！！",
		                "消息!!",JOptionPane.INFORMATION_MESSAGE);
				return;
		
	}
	else
	{
		long kk = (new Date().getTime())/86400000+30-17800;
		 long jj = (new Date().getTime())/86400000-17800;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
			 statement = connect.createStatement();
			 //statement.execute("update book_info set quantity = quantity-1 where name = '"+selected_row+"'");
			 if(statement.executeQuery("select * from record where id = '"+this.student_id+"' and book_name = '"+selected_row+"'").next())
			 {
				 JOptionPane.showMessageDialog(this,"您已借阅/预约该书，无法重复操作！！",
			                "消息!!",JOptionPane.INFORMATION_MESSAGE);
					return;
			 }
			 else
			 {
				 statement.execute("insert into record values('"+this.student_id+"','"+selected_row+"','"+jj+"','"+kk+"','否','是')");
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
		JOptionPane.showMessageDialog(this,"预约成功！！",
                "消息!!",JOptionPane.INFORMATION_MESSAGE);
		return;
	}
		}
		else if(e.getSource()==jb3)
		{
		
			if(jtxt3.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"书名不能为空！！","消息",
                        JOptionPane.INFORMATION_MESSAGE);
                      return;
			}
			else
			{
				sql="select * from book_info where name='"+jtxt3.getText()+"'";
				
				//尝试性选择-开始
				Connection connect = null;
				Statement statement  = null;
				ResultSet result = null;
				Vector<Vector> vtemp = null;
				Vector<String> accept = null;
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				 result = statement.executeQuery(sql);
				 vtemp = new Vector<Vector>();
				 while(result.next())
				 {
					 accept = new Vector<>();
					 accept.addElement(result.getString(1));
					 accept.addElement(result.getString(2));
					 accept.addElement(result.getString(3));
					 accept.addElement(result.getString(4));
					 accept.addElement(result.getString(5));
					 accept.addElement(result.getString(6));
					 accept.addElement((new Integer(result.getInt(7))).toString());
					 vtemp.add(accept);
				 }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
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
			
//尝试性选择-结束
				//从表中检索成功后，把查到的书的所有信息显示在界面下部分的表中
				//Vector<Vector> vtemp = new Vector<Vector>();
							
				dtm.setDataVector(vtemp,head);	//更新table	
				jt.updateUI();
				jt.repaint();
			}
		}
		else if(e.getSource()==jb4)
		{
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<String> accept = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				result = statement.executeQuery("select * from book_info");
				data = new Vector<Vector>();
				 while(result.next())
				 {
					 accept = new Vector<>();
					 accept.addElement(result.getString(1));
					 accept.addElement(result.getString(2));
					 accept.addElement(result.getString(3));
					 accept.addElement(result.getString(4));
					 accept.addElement(result.getString(5));
					 accept.addElement(result.getString(6));
					 accept.addElement(result.getString(7));
					 data.add(accept);
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
					dtm.setDataVector(data,head);//更新table并显示	
					jt.updateUI();
					jt.repaint();
					JOptionPane.showMessageDialog(this,"刷新成功！！",
			                "消息!!",JOptionPane.INFORMATION_MESSAGE);
					return;
		}
		
	}}


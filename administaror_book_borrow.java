package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class administaror_book_borrow extends JPanel implements ActionListener
{
	//创建分割方向为上下的JSplitePane对象
	private String id = null;
    private JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jp2=new JPanel();
	//创建按钮数组
	int flag;
	String sql;
	private JButton jb2=new JButton("确定");
	private JButton jb3=new JButton("刷新");
	//在jp2设置单选框
	private JRadioButton[] jrbArray=
  {new JRadioButton("借阅图书",true),new JRadioButton("预约图书")};
  private ButtonGroup bg=new ButtonGroup();
	Vector<String> head = new Vector<String>();	//创建标题
	{
		head.add("书号");
		head.add("书名");
		head.add("作者");
		head.add("出版社");
		head.add("出版时间");
		head.add("ISBN");
		head.add("复本量");
	}	
	Vector<Vector> data=new Vector<Vector>();//表格数据向量集合    
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
	
	
	
	
	
	
	
	
	
  public administaror_book_borrow(String adm_id)
  {
	  this.id = adm_id;
	  this.data = first_data(this.data);
	  
	  this.dtm=new DefaultTableModel(this.data,head);//创建表格模型   
		this.jt=new JTable(dtm); //创建Jtable对象	
		this.jspn=new JScrollPane(jt);//将JTable放进滚动窗体

  	this.setLayout(new GridLayout(1,1));
  	//把jsp2设置到jsp1的上部窗格
  	jsp1.setTopComponent(jp2);
  	//设置jsp1的下部窗格
  	jsp1.setBottomComponent(jspn);
  	//设置jsp1，jsp2中分割条的初始位置
  	jsp1.setDividerLocation(100);//设置分割控件位置
  	jsp1.setDividerSize(4);//设置分割控件宽度
  	jp2.setLayout(null);    	
		jb2.setBounds(380,20,100,20);//设置按钮的大小与位置
		jb3.setBounds(530, 20, 100, 20);
  	//将按钮添加进JPanel
		jp2.add(jb2);
		jp2.add(jb3);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
  	for(int i=0;i<2;i++)
  	{
  		jrbArray[i].setBounds(70+i*150,20,150,20);
  		jp2.add(jrbArray[i]);
  		bg.add(jrbArray[i]);
  	}
  	this.add(jsp1);
  	//设置窗体的标题，大小位置及可见性
      this.setBounds(10,10,800,600);
      this.setVisible(true);  
  }	
  //为事件加载的监听器加上处理事件
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb3)
		{
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<Vector> vtemp = null;
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
			JOptionPane.showMessageDialog(this," 刷新成功！！",
	                "消息!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(e.getSource()==jb2){
			if(jrbArray[0].isSelected())
			{
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
						else
						{
							JOptionPane.showMessageDialog(this,"该书暂无库存！！",
					                "消息!!",JOptionPane.INFORMATION_MESSAGE);
							return;
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
		if(judge)//判断该书是否有库存
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				 statement.execute("update book_info set quantity = quantity-1 where name = '"+selected_row+"'");
				 long kk = (new Date().getTime())/86400000+30-17800;
				 long jj = (new Date().getTime())/86400000-17800;
				 if(statement.executeQuery("select * from record where id = '"+this.id+"' and book_name = '"+selected_row+"'").next())
				 {
					 JOptionPane.showMessageDialog(this,"您已借阅/预约该书，无法重复操作！！",
				                "消息!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','是','否')");
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
			JOptionPane.showMessageDialog(this,"该书已无库存！！",
	                "消息!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
			}//选中借阅单选项操作结束
			else//选中预约单选项操作开始
			{
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
				 if(statement.executeQuery("select * from record where id = '"+this.id+"' and book_name = '"+selected_row+"'").next())
				 {
					 JOptionPane.showMessageDialog(this,"您已借阅/预约该书，无法重复操作！！",
				                "消息!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','是','否')");
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
				 if(statement.executeQuery("select * from record where id = '"+this.id+"' and book_name = '"+selected_row+"'").next())
				 {
					 JOptionPane.showMessageDialog(this,"您已借阅/预约该书，无法重复操作！！",
				                "消息!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','否','是')");
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
		}
		}
}

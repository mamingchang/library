package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class admistaror_book_control extends JPanel implements ActionListener
{
	private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jpt=new JPanel();
	String []str1=new String [7];
	String sql; 
	DataBase db;
	private JLabel[] jlArray=new JLabel[]
	{
		new JLabel("         书    号"),
		new JLabel("         书    名"),
		new JLabel("         作    者"),
		new JLabel("         出 版 社"),
		new JLabel("         购买日期"),
		new JLabel("         ISBN"),
		new JLabel("         库存")
	};
	private JTextField[] jtxtArray=new JTextField[]
	{
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField()
	};
	//设置JButton按钮的文本
	private JButton[] jbArray=
	{
	    new JButton("图书入库"),
	    new JButton("删除图书"),
	    new JButton("修改图书记录"),
	    new JButton("查找图书"),
	    new JButton("刷新")
	};
	//创建标题
	Vector<String> head = new Vector<String>();
	{
		head.add("书号");
		head.add("书名");
		head.add("作者");
		head.add("出版社");
		head.add("购买日期");
		head.add("ISBN");
		head.add("库存");
	}
	//在下部子窗口中设置表格
	Vector<Vector> data=new Vector<Vector>();
	//尝试性查询-开始		
	
			public Vector<Vector> first_data(Vector<Vector> data)
			{
				String sql = "";
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
				return data;
			}
			
			DefaultTableModel dtm = null;
			JTable jt = null;
			JScrollPane jspn = null;
			
			
			
	//尝试性查询-结束			
	
	
	
	
	

	
	
	public admistaror_book_control()
	{
		this.data= first_data(this.data);
	    //创建表格模型
	    this.dtm=new DefaultTableModel(data,head);
	    //创建Jtable对象
	    this.jt=new JTable(dtm);
		//将JTable封装到滚动窗格
	    this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
		//设置面板的上部分为空布局管理器
		jpt.setLayout(null);
		//设置jspt中分割条的初始位置
		jsp.setDividerLocation(140);
		//设置分隔条的宽度
		jsp.setDividerSize(4);
		jsp.setTopComponent(jpt);
		jsp.setBottomComponent(jspn);
		for(int i=0;i<5;i++)
		{
			jpt.add(jtxtArray[i]);
		}
		for(int i=0;i<7;i++)
		{
			jpt.add(jlArray[i]);
			if(i<3)
			{
			    jlArray[i].setBounds(15,10+30*i,100,20);
			    jtxtArray[i].setBounds(115,10+30*i,150,20);
	
			}
			else if(i>2&&i<5)
			{
				jlArray[i].setBounds(265,10+30*(i-3),100,20);
				jtxtArray[i].setBounds(375,10+30*(i-3),120,20);
			}
			else
			{
				jlArray[i].setBounds(495,10+30*(i-5),100,20);	
			}	
		}
		for(int i=0;i<5;i++)
		{
			jtxtArray[i].addActionListener(this);
		}
		this.add(jsp);
		jpt.add(jtxtArray[5]);
		jpt.add(jtxtArray[6]);
		//设置下部子窗格
    	jsp.setBottomComponent(jspn);
    	jtxtArray[5].setBounds(595,10,100,20);
    	jtxtArray[6].setBounds(595,40,100,20);
		//将JButton添加进jpt
		for(int i=0;i<5;i++)
		{
			jpt.add(jbArray[i]);
			jbArray[i].setBounds(150+112*i,100,112,25);
		}
		//设置监听器
		for(int i=0;i<5;i++)
		{
			jbArray[i].addActionListener(this);
		}		
		//设置窗体的大小位置及可见性
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
    public void actionPerformed(ActionEvent e){
    	//设置鼠标焦点
		if(e.getSource()==jtxtArray[0]){
			jtxtArray[1].requestFocus();
		}
    	if(e.getSource()==jtxtArray[1]){
    		jtxtArray[2].requestFocus();
    	}
    	if(e.getSource()==jtxtArray[2]){
    		jtxtArray[3].requestFocus();
    	}
    	if(e.getSource()==jtxtArray[3]){
    		jtxtArray[4].requestFocus();
    	}
		if(e.getSource()==jbArray[0]){//添加图书
			this.insertBook();
		} 
	    if(e.getSource()==jbArray[1]){//将书号为书号框的书从书库删除
	    	this.deleteBook();
	    	}	
	    if(e.getSource()==jbArray[2]){//将书号为书号框的书信息进行修改
	    	this.updateBook();
	    }	
	    if(e.getSource()==jbArray[3]){//查询图书信息
	    	this.searchBook();
	    }
	    if(e.getSource()==jbArray[4]){//刷新图书信息
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
	}
    public void insertBook(){
		for(int i=0;i<7;i++){//声明输入变量
            str1[i]=jtxtArray[i].getText().trim();		
		}
		if(str1[0].equals("")&&str1[1].equals("")&&str1[2].equals("")
			   &&str1[3].equals("")&&str1[4].equals("")&&str1[5].equals("")&&str1[6].equals("")){//当输入为空进行提示
			JOptionPane.showMessageDialog(this,	"图书信息不能为空！！！",
						        "消息",JOptionPane.INFORMATION_MESSAGE);
	            return;	
		}
	    if(!str1[0].equals("")&&!str1[1].equals("")&&!str1[2].equals("")
		   &&!str1[3].equals("")&&!str1[4].equals("")){//将图书信息插入bookinfo表
			sql="insert into book_info values('"+str1[0]+"','"+str1[1]+"','"
			 + str1[2] + "',' "+str1[3]+"','"+
			            str1[4]+"','"+str1[5]+"',"+str1[6]+")";
//尝试性插入-开始
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<Vector> vtemp = null;
			Vector<String> accept = null;
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
			 statement = connect.createStatement();
			 statement.executeUpdate(sql);
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
			 accept.addElement((new Integer(result.getInt(7))).toString());
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
			
			
			
			
			
			
			
			
			
//尝试性插入-结束
	dtm.setDataVector(data,head);//更新table并显示	
			jt.updateUI();
			jt.repaint();
			JOptionPane.showMessageDialog(this,	"插入成功",
			        "消息",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
    }		
	public void deleteBook(){
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,1).toString();
		String sql1="select * from record where book_name='"+selected_row+"'";
		String sql2="delete from book_info where name='"+selected_row+"'";
		String sql3 ="delete from record where book_name='"+selected_row+"'";
		
		//尝试性删除-开始
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
		Vector<Vector> vtemp = null;
		Vector<String> accept = null;
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 statement.execute(sql1);
		 statement.execute(sql2);
		 statement.execute(sql3);
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
		JOptionPane.showMessageDialog(this,"删除成功！！",
                "消息!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//尝试性删除-结束		
		
	}
	public void updateBook(){
		for(int i=0;i<7;i++){//声明输入变量
            str1[i]=jtxtArray[i].getText().trim();		
		}
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,1).toString();
		String sql1="update book_info set number = '"+str1[0]+"', author = '"+str1[2]+"', press = '"+str1[3]+"', time = '"+str1[4]+"', ISBN = '"+str1[5]+"', quantity = '"+str1[6]+"'where name='"+selected_row+"'";
		
//尝试性修改-开始
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
		Vector<Vector> vtemp = null;
		Vector<String> accept = null;
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 statement.executeUpdate(sql1);
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
		JOptionPane.showMessageDialog(this,"修改成功！！",
                "消息!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//尝试性修改-结束
		
	}
	public void searchBook(){//弹出提示对话框
		JOptionPane.showMessageDialog(this,	"请点击左边窗口的 '查询图书' 按钮！！",
						        "强烈推荐",JOptionPane.INFORMATION_MESSAGE);	
	    return;
	}
}

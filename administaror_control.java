package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class administaror_control extends JPanel implements ActionListener
{
	JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JPanel jpt=new JPanel();
	String[] str1=new String[3];
	public static String mgNo;
	String sql;
	private String administaror_id = null;
	DataBase db;
	private JLabel[] jlArray=new JLabel[]
	{
		new JLabel("       管理员名"),
		new JLabel("       权        限"),
	    new JLabel("        密       码")   
	};
	private JTextField[] jtxtArray=new JTextField[]
	{
		new JTextField(),
		new JTextField(),
		new JTextField()
	};
	private JButton[] jbArray=new JButton[]
	{
		new JButton("添加管理员"),
		new JButton("删除管理员"),
		new JButton("修改信息"),
		new JButton("查找信息"),
		new JButton("刷新")
	};
	//创建标题
	Vector<String> head=new Vector<String>();
	{
		head.add("管理员名");
		head.add("权限");	
		head.add("密码");	
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
				Vector<String> accept = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
					 statement = connect.createStatement();
					result = statement.executeQuery("select * from administraor");
					data = new Vector<Vector>();
					 while(result.next())
					 {
						 accept = new Vector<>();
						 accept.addElement(result.getString(1));
						 accept.addElement(result.getString(2));
						 accept.addElement(result.getString(3));
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
	
	public administaror_control(String mgNo)
	{
		this.administaror_id = mgNo;
		this.data = first_data(this.data);
		this.dtm=new DefaultTableModel(data,head);
	    //创建Jtable对象
		this.jt=new JTable(dtm);
		//将JTable封装到滚动窗格
		this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
	    this.mgNo=mgNo;
		//设置面板的上部分为空布局管理器
		jpt.setLayout(null);
		//设置jspt中分割条的初始位置
		jsp.setDividerLocation(115);
		//设置分隔条的宽度
		jsp.setDividerSize(4);
		jsp.setTopComponent(jpt);
		jsp.setBottomComponent(jspn);
		for(int i=0;i<3;i++)
		{
			jpt.add(jlArray[i]);
			jpt.add(jtxtArray[i]);	
		    jlArray[i].setBounds(15+i*250,20,100,25);
		    jtxtArray[i].setBounds(115+i*250,20,150,25);
		    jtxtArray[i].addActionListener(this);
		}
		this.add(jsp);
		//设置下部子窗格
    	jsp.setBottomComponent(jspn);
    	//将JButton添加进jpt,设置监听器
		for(int i=0;i<5;i++)
		{
			jpt.add(jbArray[i]);
			jbArray[i].setBounds(140+120*i,70,100,25);
			jbArray[i].addActionListener(this);
		}
	    //设置窗体的大小位置及可见性
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
	
	public void setFlag(boolean b)
	{
		jbArray[0].setEnabled(b);
		jbArray[1].setEnabled(b);		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jtxtArray[0]){jtxtArray[1].requestFocus();}//设置键盘易用性			
		if(e.getSource()==jtxtArray[1]){jtxtArray[2].requestFocus();}//设置键盘易用性	
		sql="select permit from administraor where id='"+mgNo+"'";
		db=new DataBase();
		String string="";
				if(e.getSource()==jbArray[0]){//执行添加操作
					this.insertManager();
				}
				if(e.getSource()==jbArray[1]){//执行删除操作
					this.deleteManager();
				}
				if(e.getSource()==jbArray[2]){//执行修改操作
					this.updateManager();
				}
				if(e.getSource()==jbArray[3]){//执行查询操作
					this.selectManager();
				}
				if(e.getSource()==jbArray[4]){//刷新界面操作
					Connection connect = null;
					Statement statement  = null;
					ResultSet result = null;
					Vector<String> accept = null;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
						 statement = connect.createStatement();
						result = statement.executeQuery("select * from administraor");
						data = new Vector<Vector>();
						 while(result.next())
						 {
							 accept = new Vector<>();
							 accept.addElement(result.getString(1));
							 accept.addElement(result.getString(2));
							 accept.addElement(result.getString(3));
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
			}
	public void insertManager(){
		String jtxt=jtxtArray[0].getText().trim();
		String jtxt2 = jtxtArray[2].getText().trim();
		if(jtxt.equals("")||jtxt2.equals("")){//当输入为空，提示
			JOptionPane.showMessageDialog(this,	"管理员id或密码不能为空！！！",
				        "消息",JOptionPane.INFORMATION_MESSAGE);
		    return;
		}
		for(int i=0;i<3;i++){
		    str1[i]=jtxtArray[i].getText().trim();		
		}
		sql="insert into administraor(id,permitted,password) values('"
		          +str1[0]+"','"+str1[1]+"','"+str1[2]+"')";//执行插入功能
		String sql2 = "insert into fine(id,money) values('"
		          +str1[0]+"',0)";
		//尝试性插入查找-开始				
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
		 statement.executeUpdate(sql2);
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
	result = statement.executeQuery("select * from administraor");
	data = new Vector<Vector>();
	 while(result.next())
	 {
		 accept = new Vector<>();
		 accept.addElement(result.getString(1));
		 accept.addElement(result.getString(2));
		 accept.addElement(result.getString(3));
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
		
		
		
//尝试性插入查找-结束		
			dtm.setDataVector(data,head);//更新table	
			jt.updateUI();
			jt.repaint();
			return;
	}
	public void deleteManager()
	{
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,0).toString();
		sql="delete from administraor where id= '"+selected_row+"'";
		String sql2 = "delete from record where id= '"+selected_row+"'";
		String sql3 = "Select * from record where id = '"+selected_row+"' and borrowed = '是'";
//尝试性删除-开始
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
		Vector<String> accept = null;
		int number = 0;
try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		 statement = connect.createStatement();
		 statement.executeUpdate(sql);
		 result = statement.executeQuery(sql3);
		 while(result.next())
		 {
			 number++;
		 }
		 statement.execute(sql2);
		 statement.execute("update fine set money = "+number*1000+" where id = '"+selected_row+"'");
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
	result = statement.executeQuery("select * from administraor");
	data = new Vector<Vector>();
	 while(result.next())
	 {
		 accept = new Vector<>();
		 accept.addElement(result.getString(1));
		 accept.addElement(result.getString(2));
		 accept.addElement(result.getString(3));
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
	public void updateManager(){
		String jtxt=jtxtArray[0].getText().trim();
		String jtxt3 = jtxtArray[1].getText().trim();
		String jtxt2 = jtxtArray[2].getText().trim();
		if(jtxt.equals("")||jtxt2.equals("")||jtxt3.equals("")){//当输入为空，提示
			JOptionPane.showMessageDialog(this,	"更新数据不能为空！！！",
				        "消息",JOptionPane.INFORMATION_MESSAGE);
		    return;
		}
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,0).toString();
		sql="update administraor set id = '"+selected_row+"',permitted = '"+jtxtArray[1].getText()+"', password = '"+jtxtArray[2].getText()+"' where id= '"+selected_row+"'";
//尝试性更新-开始
		Connection connect = null;
		Statement statement  = null;
		ResultSet result = null;
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
	result = statement.executeQuery("select * from administraor");
	data = new Vector<Vector>();
	 while(result.next())
	 {
		 accept = new Vector<>();
		 accept.addElement(result.getString(1));
		 accept.addElement(result.getString(2));
		 accept.addElement(result.getString(3));
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
		
		
		
		
		
//尝试性更新-结束
		
	}
	public void selectManager(){
		if(jtxtArray[0].getText().equals("")){//提示
			JOptionPane.showMessageDialog(this,"管理员ID不能为空，请重新输入！！！",
			                              "信息",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		sql="select * from administraor where id= '"+Integer.parseInt(jtxtArray[0].getText().trim())+"'";
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
    		data = new Vector<Vector>();
    		 while(result.next())
    		 {
    			 accept = new Vector<>();
    			 accept.addElement(result.getString(1));
    			 accept.addElement(result.getString(2));
    			 accept.addElement(result.getString(3));
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
    			return;	    	
	}	
		
		
		
		
		
		
		
    public void table(){
    	try{
		     int k=0;
			 Vector<Vector> vtemp = new Vector<Vector>();
			 
			 if(k==0){//若manager表中没有该管理员号，则输出提示对话框
			 	 JOptionPane.showMessageDialog(this,"没有您要查找的内容",
				                 "消息",JOptionPane.INFORMATION_MESSAGE);
			 }
			dtm.setDataVector(vtemp,head);
			jt.updateUI();
			jt.repaint();				 	
		}
		catch(Exception e){e.printStackTrace();}	
    }
    public static void main(String[]args)
    {
    	new administaror_control(mgNo);
    }
}

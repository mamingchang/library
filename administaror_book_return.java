package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
public class administaror_book_return extends JPanel implements ActionListener
{
	DataBase db;
	String sql;
	String str;
	//创建分割方向为上下的JSplitePane对象
    private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
    //创建JPanel对象
	private JPanel jpt=new JPanel();
	private JPanel jpb=new JPanel();
	//创建按钮数组
	private JButton[] jbArray=new JButton[]
	{
		new JButton	("挂失"),
		new JButton	("归还"),
		new JButton	("确定"),
		new JButton ("刷新")
	};
	private JLabel jl=new JLabel("查询的书名:");

	private JTextField jtxt=new JTextField();
	private String administaror_id = null;

	//创建标题
	Vector<String> head = new Vector<String>();
	{
		head.add("ID");
		head.add("书名");
		head.add("借阅时间");
		head.add("还书时间");
		head.add("借阅");
		head.add("预约");
	}
	//
	Vector<Vector> data=new Vector<Vector>();
	//尝试性查询-开始		
	
		public Vector<Vector> first_data(Vector<Vector> data,String id)
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
				result = statement.executeQuery("select * from record where id = '"+id+"'");
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
	//创建表格模型
    
	public administaror_book_return(String new_id)
	{
		this.administaror_id = new_id;
		this.data = first_data(this.data,this.administaror_id);
		this.dtm=new DefaultTableModel(data,head);
	    //创建Jtable对象
		this.jt=new JTable(dtm);
		//将JTable封装到滚动窗格
		this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
		//设置整个RetrunBook界面上下部分均为空布局管理器
		jpt.setLayout(null);
		jpb.setLayout(null);
		//设置Label的大小及位置
           jl.setBounds(5,15,100,20);	
		//将Jlabel添加到jpt面板上
		 jpt.add(jl);
		//为JTextField设置大小及位置
		jtxt.setBounds(105,15,300,20);
    	//把JTextField添加到jpt
	     jpt.add(jtxt);
        //设置JBuuton的大小及位置
	    jbArray[0].setBounds(5,50,100,20);
        jbArray[1].setBounds(150,50,100,20);
        jbArray[2].setBounds(295,50,100,20);
        jbArray[3].setBounds(440, 50, 100, 20);
        //添加JButton并给其添加事件监听器
         for(int i=0;i<4;i++)
		{
			 jpt.add(jbArray[i]);
			 jbArray[i].addActionListener(this);
		}

		//把jpt设置到jsp的上部窗格
    	jsp.setTopComponent(jpt);
        jsp.setBottomComponent(jspn);
    	jsp.setDividerSize(4);
    	this.add(jsp);
    	//设置jsp中分割条的初始位置
    	jsp.setDividerLocation(80);
		//设置窗体的大小位置及可见性
        this.setBounds(10,10,800,600);
        this.setVisible(true); 
   	} 
   	public void actionPerformed(ActionEvent e)
   	{
   		if(e.getSource()==jbArray[3])
   		{
   			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<String> accept = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
				 statement = connect.createStatement();
				result = statement.executeQuery("select * from record where id = '"+this.administaror_id+"'");
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
   		if(e.getSource()==jbArray[2]){//事件源为"确定"按钮
   			if(jtxt.getText().trim().equals("")){//学号输入为空，提示
   				JOptionPane.showMessageDialog(this,"请输入要查询的书名",
					            "消息",JOptionPane.INFORMATION_MESSAGE);
					return;
   			}
   			else{//根据学号进行查询
   				sql="select * from record where book_name="+jtxt.getText().trim();
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
				dtm.setDataVector(vtemp,head);	//更新table	
				jt.updateUI();
				jt.repaint();
			}
   		}
   		if(e.getSource()==jbArray[1])
   		{//当要归还图书
   			long jj = (new Date().getTime())/86400000-17800;
   			//记录当前时间来计算是否超期
   			int row = jt.getSelectedRow();
			String selected_id = jt.getValueAt(row,0).toString();
			String selected_book_name = jt.getValueAt(row,1).toString();
			String selected_return_time = jt.getValueAt(row,3).toString();
   			boolean judge = false;
   			String find = "select ordered from record where id = '"+selected_id+"' and book_name = '"+selected_book_name+"'";
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
   				String ad = result.getString(1);
   				if(ad.equals("是"))
   				{
   					judge = true;
   				}
   			}
   			else
   			{
   				JOptionPane.showMessageDialog(this,"未找到该生！！！","信息",
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
   			
   			
   			
   			
   			if(judge)
   			{//当对预约的图书进行操作时

 //尝试性删除-开始
   				Vector<Vector> vtemp = null;
   				Vector<String> accept = null;
   		try {
   				Class.forName("com.mysql.cj.jdbc.Driver");
   				 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
   				 statement = connect.createStatement();
   				 statement.execute("delete from record where id = '"+this.administaror_id+"' and book_name = '"+selected_book_name+"'");
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
   			result = statement.executeQuery("select * from record where id = '"+this.administaror_id+"'");
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
   			else
   			{//当对借阅的图书进行操作时
   				if((new Long(selected_return_time)).longValue()<jj)
   				{//判断借阅的图书是否超期,超期操作
   				long cha = jj-(new Long(selected_return_time)).longValue();
   				Vector<String>accept = null;
   				String gengxincaozuo = "update fine set money = money+"+cha+" where id = '"+this.administaror_id+"'";
   				//更新用户的罚款表
   				String genggaiquanxian = "update administraor set permitted = '否'  where id = '"+this.administaror_id+"'";
   				//更改用户的权限为否
   				{//查看是否有人预约该书，若有，则将该书借给这个人,否则将该书的库存量+1
   					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		   	   			 statement = connect.createStatement();
		   	   			result = statement.executeQuery("select * from record where book_name = '"+selected_book_name+"' and ordered= '是' ");
//注意此处是要更改的查询是否有高级权限的用户有预约的信息时
		   	   			if(result.next())
		   	   			{//查询到有学生预约这本书,将这本书给这名学生，并删除该记录
		   	   			long new_kk = (new Date().getTime())/86400000+30-17800;
						 long new_jj = (new Date().getTime())/86400000-17800;
		   	   				String change_id = result.getString(1);
		   	   				statement.execute("update record fine set ordered = '否',borrowed = '是',borrowtime = '"+new_jj+"',returntime = '"+new_kk+"'");
		   	   				
		   	   			}
		   	   			else
		   	   			{
		   	   				statement.execute("update book_info set quantity = quantity+1 where name = '"+selected_book_name+"'");
		   	   			}
		   	   			statement.execute(gengxincaozuo);
		   	   		statement.execute(genggaiquanxian);
		   	   		//删除该记录
		   	   	statement.execute("delete from record where id = '"+this.administaror_id+"' and book_name = '"+selected_book_name+"'");
		   	 result = statement.executeQuery("select * from record where id = '"+this.administaror_id+"'");
   			 data = new Vector<Vector>();
   			 accept=null; 
   			 while(result.next())
   			 {
   				 accept = new Vector<>();
   				 accept.addElement(result.getString(1));
   				 accept.addElement(result.getString(2));
   				 accept.addElement(result.getString(3));
   				 accept.addElement(result.getString(4));
   				 accept.addElement(result.getString(5));
   				 accept.addElement(result.getString(6));
   				 data.add(accept);
   			 }
   					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
   					
   					dtm.setDataVector(data,head);//更新table并显示	
   	   				jt.updateUI();
   	   				jt.repaint();
   	   				JOptionPane.showMessageDialog(this,"删除成功！！",
   	   		                "消息!!",JOptionPane.INFORMATION_MESSAGE);
   	   				return;
   	   			 
   				}
   				}
   				else
   				//设计当归还的书本时间未超过归还时间时要进行的事务处理
   				{
   	   				{//查看是否有人预约该书，若有，则将该书借给这个人,否则将该书的库存量+1
   	   					try {
   							Class.forName("com.mysql.cj.jdbc.Driver");
   							connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
   			   	   			 statement = connect.createStatement();
   			   	   			result = statement.executeQuery("select * from record where book_name = '"+selected_book_name+"' and ordered= '是' ");
//注意此处是要更改的查询是否有高级权限的用户有预约的信息时
   			   	   			if(result.next())
   			   	   			{//查询到有学生预约这本书,将这本书给这名学生，并删除该记录
   			   	   			long new_kk = (new Date().getTime())/86400000+30-17800;
   							 long new_jj = (new Date().getTime())/86400000-17800;
   			   	   				String change_id = result.getString(1);
   			   	   				statement.execute("update record fine set ordered = '否',borrowed = '是',borrowtime = '"+new_jj+"',returntime = '"+new_kk+"'");
   			   	   				
   			   	   			}
   			   	   			else
   			   	   			{
   			   	   				statement.execute("update book_info set quantity = quantity+1 where name = '"+selected_book_name+"'");
   			   	   			}
   			   	   		//删除该记录
   			   	   	statement.execute("delete from record where id = '"+this.administaror_id+"' and book_name = '"+selected_book_name+"'");
   			   	result = statement.executeQuery("select * from record where id = '"+this.administaror_id+"'");
   			 data = new Vector<Vector>();
   			 Vector <String> accept=null; 
   			 while(result.next())
   			 {
   				 accept = new Vector<>();
   				 accept.addElement(result.getString(1));
   				 accept.addElement(result.getString(2));
   				 accept.addElement(result.getString(3));
   				 accept.addElement(result.getString(4));
   				 accept.addElement(result.getString(5));
   				 accept.addElement(result.getString(6));
   				 data.add(accept);
   			 }
   	   					} catch (Exception e1) {
   							// TODO Auto-generated catch block
   							e1.printStackTrace();
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
   	   				dtm.setDataVector(data,head);//更新table并显示	
   	   				jt.updateUI();
   	   				jt.repaint();
   	   				JOptionPane.showMessageDialog(this,"删除成功！！",
   	   		                "消息!!",JOptionPane.INFORMATION_MESSAGE);
   	   				return;
   	   				
   	   				}
   				}
   			}
   			
   			
   			
   			
   			
   			
			
   		}
   		if(e.getSource()==jbArray[0])
   		{//需要挂失图书
			int row = jt.getSelectedRow();
			String selected_id = jt.getValueAt(row,0).toString();
			String selected_book_name = jt.getValueAt(row,1).toString();
			String power = jt.getValueAt(row,5).toString();
			if(row<0)
			{
				JOptionPane.showMessageDialog(this,"请选择要挂失的书!!!","消息",
					                              JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else
			{
				if(power.equals("是"))
				{
					JOptionPane.showMessageDialog(this,"不可对预约的图书进行挂失操作!!!","消息",
                            JOptionPane.INFORMATION_MESSAGE);
                          return;
				}
				String sql1= "delete from record where id = '"+selected_id+"' and book_name = '"+selected_book_name+"'";
				String sql2="update fine set money = money+1000 where id = '"+this.administaror_id+"'";
				String sql3="update administraor set permitted where id = '"+this.administaror_id+"'";
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
				 statement.execute(sql1);
				 statement.execute(sql2);
				 statement.execute(sql3);
				 result = statement.executeQuery("select * from record where id = '"+this.administaror_id+"'");
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
				dtm.setDataVector(vtemp,head);	//更新table	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"挂失成功!!!","消息",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
				
				
				
			}
			
   		}
   	}
   	public static void main(String[] args)
   	{
   	}
}

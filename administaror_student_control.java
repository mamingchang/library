package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class administaror_student_control extends JPanel implements ActionListener
{
	//创建一个上下方向分割的JSplitPane对象
		private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		private JPanel jpt=new JPanel();
		String[]str1=new String[7];//声明字符串数组
		String sql;
		DataBase db;
		private JLabel[] jlArray=new JLabel[]{//声明标签数组
			new JLabel("              学    号"),
			new JLabel("              姓    名"),
			new JLabel("              性    别"),
			new JLabel("              班    级"),
			new JLabel("              院    系"),
		    new JLabel("              密    码"),
		    new JLabel("              借书权限")   
		};
		private JTextField[] jtxtArray=new JTextField[]{//声明文本框数组
			new JTextField(),new JTextField(),
			new JTextField(),new JTextField(),
			new JTextField(),new JTextField()
		};
		private String[] str={"是","否"};//创建下拉列表框数据模型的字符串数组
		private JComboBox jcp=new JComboBox(str);//创建下拉列表框
		private JButton[] jbArray={//设置JButton按钮的文本
		    new JButton("添加学生信息"),new JButton("删除学生信息"),
		    new JButton("修改学生信息"),new JButton("查找学生信息"),
		    new JButton("刷新")
		};
		Vector<String> head = new Vector<String>();
		{//创建标题
			head.add("学号");head.add("姓名");
			head.add("性别");head.add("班级");
			head.add("院系");head.add("密码");
			head.add("借书权限");
		}
		Vector<Vector> data=new Vector<Vector>();//在下部子窗口中设置表格
		
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
				result = statement.executeQuery("select * from student");
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
					 accept.addElement(result.getString(8));
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
	    
		public administaror_student_control()
		{
			this.data = first_data(this.data);
			this.dtm=new DefaultTableModel(data,head);//创建表格模型
			this.jt=new JTable(dtm);//创建JTable对象
			this.jspn=new JScrollPane(jt);//将JTable封装到滚动窗格
			this.setLayout(new GridLayout(1,1));//声明本界面为网格布局
			jpt.setLayout(null);//设置面板的上部分为空布局管理器
			jsp.setDividerLocation(130);//设置jspt中分割条的初始位置
			jsp.setDividerSize(4);//设置分隔条的宽度
			jsp.setTopComponent(jpt);
			jsp.setBottomComponent(jspn);
			for(int i=0;i<6;i++){//将文本框添加进上部面板
				jpt.add(jtxtArray[i]);
			}
			for(int i=0;i<7;i++){
				jpt.add(jlArray[i]);
				if(i<3)
				{//对界面上的第一行标签和文本框大小位置进行设置
				    jlArray[i].setBounds(20+i*200,10,100,20);
				    jtxtArray[i].setBounds(120+i*200,10,120,20);
				    jtxtArray[i].addActionListener(this);
				}
				else if(i>2&&i<6)
				{//对第二行标签和文本框大小位置进行设置
					jlArray[i].setBounds(20+(i-3)*200,50,100,20);
					jtxtArray[i].setBounds(120+(i-3)*200,50,120,20);
					jtxtArray[i].addActionListener(this);
				}
				else
				{//对最下面的显示标签进行设置
					jlArray[i].setBounds(620,10,100,20);
				}
			}
			this.add(jsp);
			jpt.add(jcp);
	    	jsp.setBottomComponent(jspn);//设置下部子窗格
			jcp.setBounds(720,10,100,20);
			for(int i=0;i<5;i++)
			{//将JButton添加进jpt
				jpt.add(jbArray[i]);
				jbArray[i].setBounds(170+112*i,90,112,25);
				jbArray[i].addActionListener(this);	//设置监听器
			}		
			//设置窗体的大小位置及可见性
			this.setBounds(5,5,600,500);
			this.setVisible(true);
		}
		public void actionPerformed(ActionEvent e)
		{//为文本框设置焦点
			if(e.getSource()==jtxtArray[0])
	    	{
	    		jtxtArray[1].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[1])
	    	{
	    		jtxtArray[2].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[2])
	    	{
	    		jtxtArray[3].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[3])
	    	{
	    		jtxtArray[4].requestFocus();
	    	}
	    	if(e.getSource()==jtxtArray[4])
	    	{
	    		jtxtArray[5].requestFocus();
	    	}   
		    //当点击"添加学生信息"按钮是将执行添加功能，将文本框的学生信息添加进STUDENT表中
			if(e.getSource()==jbArray[0])
			{
				this.insertStudent();
			}
			//当点击"删除学生信息"按钮是将执行删除功能，将学号为学号框的学生信息从STUDENT表中删除	
			if(e.getSource()==jbArray[1])
			{
				this.deleteStudent();
			}
			//当点击"修改学生信息"按钮是将执行修改功能，将信息为学号框的学生信息在STUDENT表中更新
			if(e.getSource()==jbArray[2])
			{
				this.updateStudent();
			}
			//当点击"查找学生信息"按钮是将执行查找功能，将从STUDENT表中查找学号为学号框的学生信息
			if(e.getSource()==jbArray[3])
			{
				this.searchStudent();
			}
			if(e.getSource()==jbArray[4])
			{//刷新操作
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
					result = statement.executeQuery("select * from student");
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
						 accept.addElement(result.getString(8));
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
		public void insertStudent(){
	        for(int i=0;i<6;i++){//声明文本框输入信息
				str1[i]=jtxtArray[i].getText().trim();		
			}
	    	if(str1[0].equals("")&&str1[1].equals("")&&str1[2].equals("")
			   &&str1[3].equals("")&&str1[4].equals("")&&str1[5].equals(""))
			{//当各文本框为空提示
			  	JOptionPane.showMessageDialog(this,	"学生信息不能为空！！！",
								        "消息",JOptionPane.INFORMATION_MESSAGE);
				return;	
			}
			if(!str1[0].equals("")&&!str1[1].equals("")&&!str1[2].equals("")
			   &&!str1[3].equals("")&&!str1[4].equals("")&&!str1[5].equals(""))
			{//当在文本框输入信息
				str1[6]=jcp.getSelectedItem().toString();
				sql="insert into student(xuehao,name,sex,class,department,"
				+"password,permit) values('"+str1[0]+"','"+str1[1]+"','"
				 + str1[2] + "',' "+str1[3]+"','"+
				            str1[4]+"','"+str1[5]+"','"+str1[6]+"')";
				String sql2 = "insert into fine(id,money)values('"+str1[0]+"',0)";
				
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
			result = statement.executeQuery("select * from student");
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
				 accept.addElement(result.getString(8));
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

				dtm.setDataVector(data,head);//更新table并显示	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"插入成功！！",
                        "消息!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	    }
		public void deleteStudent(){
			int row = jt.getSelectedRow();
			String selected_row = jt.getValueAt(row,0).toString();
			sql="delete from student where xuehao= '"+selected_row+"'";
			String sql2 = "delete from record where id= '"+selected_row+"'";
			String sql3 = "Select * from record where id = '"+selected_row+"' and borrowed = '是'";
//尝试性删除-开始
			Connection connect = null;
			Statement statement  = null;
			ResultSet result = null;
			Vector<Vector> vtemp = null;
			Vector<String> accept = null;
			int number = 0;
	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
			 statement = connect.createStatement();
			 statement.execute(sql);
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
		result = statement.executeQuery("select * from student");
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
			 accept.addElement(result.getString(8));
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
		public void updateStudent(){
			String str[]=new String[7];
			int row = jt.getSelectedRow();//声明所选行号
			if(row>=0){//选择了表格中的某行
				for(int i=0;i<7;i++){str[i]=jt.getValueAt(row,i).toString();}
				sql="update student set name='"+Integer.parseInt(jtxtArray[2].getText().trim())+"',sex='"+Integer.parseInt(jtxtArray[4].getText().trim())+"',class='"
				     +Integer.parseInt(jtxtArray[1].getText().trim())+"',department='"+Integer.parseInt(jtxtArray[3].getText().trim())+"',permit='"+str[6]+"',password='"+Integer.parseInt(jtxtArray[5].getText().trim())
				     +"' where xuehao='"+Integer.parseInt(str[0].trim())+"'";
				//db=new DataBase();
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
			result = statement.executeQuery("select * from student");
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
				 accept.addElement(result.getString(8));
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
				
							
				//db=new DataBase();
				//Vector<String> v = new Vector<String>();
			    //for(int i=0;i<=6;i++){//将每列添加到临时数组v
				//	v.add(str1[i]);
				//	if(i<6){jtxtArray[i].setText("");}	
			    //}
			    //data.add(v);
				dtm.setDataVector(data,head);//更新table并显示	
			    //dtm.setDataVector(vtemp,head);
				jt.updateUI();
				jt.repaint();
								
				
				
				
				
			
				
//尝试性修改-结束				
				JOptionPane.showMessageDialog(this,"修改成功！！",
				                                   "消息!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(row==-1){//当没有选择就点击'修改信息'按钮 ，提示
				JOptionPane.showMessageDialog(this,"请点击'查找'按钮,在下部更改,再选中所改行,点击'修改信息'按钮",
				                               "Warning!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}		
		}
		public void searchStudent(){
			if(jtxtArray[0].getText().equals("")){//
				JOptionPane.showMessageDialog(this,"输入不能为空，请重新输入！！！",
				                              "信息",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	       	sql="select * from student where xuehao= '"+Integer.parseInt(jtxtArray[0].getText().trim())+"'";
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
	    			 accept.addElement(result.getString(4));
	    			 accept.addElement(result.getString(5));
	    			 accept.addElement(result.getString(6));
	    			 accept.addElement(result.getString(8));
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
	    			
	    						
	    			//db=new DataBase();
	    			//Vector<String> v = new Vector<String>();
	    		    //for(int i=0;i<=6;i++){//将每列添加到临时数组v
	    			//	v.add(str1[i]);
	    			//	if(i<6){jtxtArray[i].setText("");}	
	    		    //}
	    		    //data.add(v);
	    			dtm.setDataVector(data,head);//更新table并显示	
	    		    //dtm.setDataVector(vtemp,head);
	    			jt.updateUI();
	    			jt.repaint();
	    			return;	    	
		}
}

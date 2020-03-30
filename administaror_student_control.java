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
	//����һ�����·���ָ��JSplitPane����
		private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		private JPanel jpt=new JPanel();
		String[]str1=new String[7];//�����ַ�������
		String sql;
		DataBase db;
		private JLabel[] jlArray=new JLabel[]{//������ǩ����
			new JLabel("              ѧ    ��"),
			new JLabel("              ��    ��"),
			new JLabel("              ��    ��"),
			new JLabel("              ��    ��"),
			new JLabel("              Ժ    ϵ"),
		    new JLabel("              ��    ��"),
		    new JLabel("              ����Ȩ��")   
		};
		private JTextField[] jtxtArray=new JTextField[]{//�����ı�������
			new JTextField(),new JTextField(),
			new JTextField(),new JTextField(),
			new JTextField(),new JTextField()
		};
		private String[] str={"��","��"};//���������б������ģ�͵��ַ�������
		private JComboBox jcp=new JComboBox(str);//���������б��
		private JButton[] jbArray={//����JButton��ť���ı�
		    new JButton("���ѧ����Ϣ"),new JButton("ɾ��ѧ����Ϣ"),
		    new JButton("�޸�ѧ����Ϣ"),new JButton("����ѧ����Ϣ"),
		    new JButton("ˢ��")
		};
		Vector<String> head = new Vector<String>();
		{//��������
			head.add("ѧ��");head.add("����");
			head.add("�Ա�");head.add("�༶");
			head.add("Ժϵ");head.add("����");
			head.add("����Ȩ��");
		}
		Vector<Vector> data=new Vector<Vector>();//���²��Ӵ��������ñ��
		
//�����Բ�ѯ-��ʼ		
		
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
		
		
		
//�����Բ�ѯ-����		
	    
		public administaror_student_control()
		{
			this.data = first_data(this.data);
			this.dtm=new DefaultTableModel(data,head);//�������ģ��
			this.jt=new JTable(dtm);//����JTable����
			this.jspn=new JScrollPane(jt);//��JTable��װ����������
			this.setLayout(new GridLayout(1,1));//����������Ϊ���񲼾�
			jpt.setLayout(null);//���������ϲ���Ϊ�ղ��ֹ�����
			jsp.setDividerLocation(130);//����jspt�зָ����ĳ�ʼλ��
			jsp.setDividerSize(4);//���÷ָ����Ŀ��
			jsp.setTopComponent(jpt);
			jsp.setBottomComponent(jspn);
			for(int i=0;i<6;i++){//���ı�����ӽ��ϲ����
				jpt.add(jtxtArray[i]);
			}
			for(int i=0;i<7;i++){
				jpt.add(jlArray[i]);
				if(i<3)
				{//�Խ����ϵĵ�һ�б�ǩ���ı����Сλ�ý�������
				    jlArray[i].setBounds(20+i*200,10,100,20);
				    jtxtArray[i].setBounds(120+i*200,10,120,20);
				    jtxtArray[i].addActionListener(this);
				}
				else if(i>2&&i<6)
				{//�Եڶ��б�ǩ���ı����Сλ�ý�������
					jlArray[i].setBounds(20+(i-3)*200,50,100,20);
					jtxtArray[i].setBounds(120+(i-3)*200,50,120,20);
					jtxtArray[i].addActionListener(this);
				}
				else
				{//�����������ʾ��ǩ��������
					jlArray[i].setBounds(620,10,100,20);
				}
			}
			this.add(jsp);
			jpt.add(jcp);
	    	jsp.setBottomComponent(jspn);//�����²��Ӵ���
			jcp.setBounds(720,10,100,20);
			for(int i=0;i<5;i++)
			{//��JButton��ӽ�jpt
				jpt.add(jbArray[i]);
				jbArray[i].setBounds(170+112*i,90,112,25);
				jbArray[i].addActionListener(this);	//���ü�����
			}		
			//���ô���Ĵ�Сλ�ü��ɼ���
			this.setBounds(5,5,600,500);
			this.setVisible(true);
		}
		public void actionPerformed(ActionEvent e)
		{//Ϊ�ı������ý���
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
		    //�����"���ѧ����Ϣ"��ť�ǽ�ִ����ӹ��ܣ����ı����ѧ����Ϣ��ӽ�STUDENT����
			if(e.getSource()==jbArray[0])
			{
				this.insertStudent();
			}
			//�����"ɾ��ѧ����Ϣ"��ť�ǽ�ִ��ɾ�����ܣ���ѧ��Ϊѧ�ſ��ѧ����Ϣ��STUDENT����ɾ��	
			if(e.getSource()==jbArray[1])
			{
				this.deleteStudent();
			}
			//�����"�޸�ѧ����Ϣ"��ť�ǽ�ִ���޸Ĺ��ܣ�����ϢΪѧ�ſ��ѧ����Ϣ��STUDENT���и���
			if(e.getSource()==jbArray[2])
			{
				this.updateStudent();
			}
			//�����"����ѧ����Ϣ"��ť�ǽ�ִ�в��ҹ��ܣ�����STUDENT���в���ѧ��Ϊѧ�ſ��ѧ����Ϣ
			if(e.getSource()==jbArray[3])
			{
				this.searchStudent();
			}
			if(e.getSource()==jbArray[4])
			{//ˢ�²���
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
				dtm.setDataVector(data,head);//����table����ʾ	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"ˢ�³ɹ�����",
                        "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
				
			}
		}
		public void insertStudent(){
	        for(int i=0;i<6;i++){//�����ı���������Ϣ
				str1[i]=jtxtArray[i].getText().trim();		
			}
	    	if(str1[0].equals("")&&str1[1].equals("")&&str1[2].equals("")
			   &&str1[3].equals("")&&str1[4].equals("")&&str1[5].equals(""))
			{//�����ı���Ϊ����ʾ
			  	JOptionPane.showMessageDialog(this,	"ѧ����Ϣ����Ϊ�գ�����",
								        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				return;	
			}
			if(!str1[0].equals("")&&!str1[1].equals("")&&!str1[2].equals("")
			   &&!str1[3].equals("")&&!str1[4].equals("")&&!str1[5].equals(""))
			{//�����ı���������Ϣ
				str1[6]=jcp.getSelectedItem().toString();
				sql="insert into student(xuehao,name,sex,class,department,"
				+"password,permit) values('"+str1[0]+"','"+str1[1]+"','"
				 + str1[2] + "',' "+str1[3]+"','"+
				            str1[4]+"','"+str1[5]+"','"+str1[6]+"')";
				String sql2 = "insert into fine(id,money)values('"+str1[0]+"',0)";
				
//�����Բ������-��ʼ				
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
				
				
				
//�����Բ������-����				

				dtm.setDataVector(data,head);//����table����ʾ	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"����ɹ�����",
                        "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
	    }
		public void deleteStudent(){
			int row = jt.getSelectedRow();
			String selected_row = jt.getValueAt(row,0).toString();
			sql="delete from student where xuehao= '"+selected_row+"'";
			String sql2 = "delete from record where id= '"+selected_row+"'";
			String sql3 = "Select * from record where id = '"+selected_row+"' and borrowed = '��'";
//������ɾ��-��ʼ
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
			dtm.setDataVector(data,head);//����table����ʾ	
			jt.updateUI();
			jt.repaint();
			JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
                    "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
			
			
			
			
			
//������ɾ��-����
		}
		public void updateStudent(){
			String str[]=new String[7];
			int row = jt.getSelectedRow();//������ѡ�к�
			if(row>=0){//ѡ���˱���е�ĳ��
				for(int i=0;i<7;i++){str[i]=jt.getValueAt(row,i).toString();}
				sql="update student set name='"+Integer.parseInt(jtxtArray[2].getText().trim())+"',sex='"+Integer.parseInt(jtxtArray[4].getText().trim())+"',class='"
				     +Integer.parseInt(jtxtArray[1].getText().trim())+"',department='"+Integer.parseInt(jtxtArray[3].getText().trim())+"',permit='"+str[6]+"',password='"+Integer.parseInt(jtxtArray[5].getText().trim())
				     +"' where xuehao='"+Integer.parseInt(str[0].trim())+"'";
				//db=new DataBase();
//�������޸�-��ʼ				
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
			    //for(int i=0;i<=6;i++){//��ÿ����ӵ���ʱ����v
				//	v.add(str1[i]);
				//	if(i<6){jtxtArray[i].setText("");}	
			    //}
			    //data.add(v);
				dtm.setDataVector(data,head);//����table����ʾ	
			    //dtm.setDataVector(vtemp,head);
				jt.updateUI();
				jt.repaint();
								
				
				
				
				
			
				
//�������޸�-����				
				JOptionPane.showMessageDialog(this,"�޸ĳɹ�����",
				                                   "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(row==-1){//��û��ѡ��͵��'�޸���Ϣ'��ť ����ʾ
				JOptionPane.showMessageDialog(this,"����'����'��ť,���²�����,��ѡ��������,���'�޸���Ϣ'��ť",
				                               "Warning!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}		
		}
		public void searchStudent(){
			if(jtxtArray[0].getText().equals("")){//
				JOptionPane.showMessageDialog(this,"���벻��Ϊ�գ����������룡����",
				                              "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
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
	    		    //for(int i=0;i<=6;i++){//��ÿ����ӵ���ʱ����v
	    			//	v.add(str1[i]);
	    			//	if(i<6){jtxtArray[i].setText("");}	
	    		    //}
	    		    //data.add(v);
	    			dtm.setDataVector(data,head);//����table����ʾ	
	    		    //dtm.setDataVector(vtemp,head);
	    			jt.updateUI();
	    			jt.repaint();
	    			return;	    	
		}
}

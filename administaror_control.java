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
		new JLabel("       ����Ա��"),
		new JLabel("       Ȩ        ��"),
	    new JLabel("        ��       ��")   
	};
	private JTextField[] jtxtArray=new JTextField[]
	{
		new JTextField(),
		new JTextField(),
		new JTextField()
	};
	private JButton[] jbArray=new JButton[]
	{
		new JButton("��ӹ���Ա"),
		new JButton("ɾ������Ա"),
		new JButton("�޸���Ϣ"),
		new JButton("������Ϣ"),
		new JButton("ˢ��")
	};
	//��������
	Vector<String> head=new Vector<String>();
	{
		head.add("����Ա��");
		head.add("Ȩ��");	
		head.add("����");	
	}
	//���²��Ӵ��������ñ��
	Vector<Vector> data=new Vector<Vector>();
//�����Բ�ѯ-��ʼ		
	
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
			
			
			
//�����Բ�ѯ-����
	
	public administaror_control(String mgNo)
	{
		this.administaror_id = mgNo;
		this.data = first_data(this.data);
		this.dtm=new DefaultTableModel(data,head);
	    //����Jtable����
		this.jt=new JTable(dtm);
		//��JTable��װ����������
		this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
	    this.mgNo=mgNo;
		//���������ϲ���Ϊ�ղ��ֹ�����
		jpt.setLayout(null);
		//����jspt�зָ����ĳ�ʼλ��
		jsp.setDividerLocation(115);
		//���÷ָ����Ŀ��
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
		//�����²��Ӵ���
    	jsp.setBottomComponent(jspn);
    	//��JButton��ӽ�jpt,���ü�����
		for(int i=0;i<5;i++)
		{
			jpt.add(jbArray[i]);
			jbArray[i].setBounds(140+120*i,70,100,25);
			jbArray[i].addActionListener(this);
		}
	    //���ô���Ĵ�Сλ�ü��ɼ���
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
	
	public void setFlag(boolean b)
	{
		jbArray[0].setEnabled(b);
		jbArray[1].setEnabled(b);		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jtxtArray[0]){jtxtArray[1].requestFocus();}//���ü���������			
		if(e.getSource()==jtxtArray[1]){jtxtArray[2].requestFocus();}//���ü���������	
		sql="select permit from administraor where id='"+mgNo+"'";
		db=new DataBase();
		String string="";
				if(e.getSource()==jbArray[0]){//ִ����Ӳ���
					this.insertManager();
				}
				if(e.getSource()==jbArray[1]){//ִ��ɾ������
					this.deleteManager();
				}
				if(e.getSource()==jbArray[2]){//ִ���޸Ĳ���
					this.updateManager();
				}
				if(e.getSource()==jbArray[3]){//ִ�в�ѯ����
					this.selectManager();
				}
				if(e.getSource()==jbArray[4]){//ˢ�½������
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
					dtm.setDataVector(data,head);//����table����ʾ	
					jt.updateUI();
					jt.repaint();
					JOptionPane.showMessageDialog(this,"ˢ�³ɹ�����",
			                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
					
					
					
				}
			}
	public void insertManager(){
		String jtxt=jtxtArray[0].getText().trim();
		String jtxt2 = jtxtArray[2].getText().trim();
		if(jtxt.equals("")||jtxt2.equals("")){//������Ϊ�գ���ʾ
			JOptionPane.showMessageDialog(this,	"����Աid�����벻��Ϊ�գ�����",
				        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
		    return;
		}
		for(int i=0;i<3;i++){
		    str1[i]=jtxtArray[i].getText().trim();		
		}
		sql="insert into administraor(id,permitted,password) values('"
		          +str1[0]+"','"+str1[1]+"','"+str1[2]+"')";//ִ�в��빦��
		String sql2 = "insert into fine(id,money) values('"
		          +str1[0]+"',0)";
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
		
		
		
//�����Բ������-����		
			dtm.setDataVector(data,head);//����table	
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
		String sql3 = "Select * from record where id = '"+selected_row+"' and borrowed = '��'";
//������ɾ��-��ʼ
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
		dtm.setDataVector(data,head);//����table����ʾ	
		jt.updateUI();
		jt.repaint();
		JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//������ɾ��-����
	}
	public void updateManager(){
		String jtxt=jtxtArray[0].getText().trim();
		String jtxt3 = jtxtArray[1].getText().trim();
		String jtxt2 = jtxtArray[2].getText().trim();
		if(jtxt.equals("")||jtxt2.equals("")||jtxt3.equals("")){//������Ϊ�գ���ʾ
			JOptionPane.showMessageDialog(this,	"�������ݲ���Ϊ�գ�����",
				        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
		    return;
		}
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,0).toString();
		sql="update administraor set id = '"+selected_row+"',permitted = '"+jtxtArray[1].getText()+"', password = '"+jtxtArray[2].getText()+"' where id= '"+selected_row+"'";
//�����Ը���-��ʼ
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
		dtm.setDataVector(data,head);//����table����ʾ	
		jt.updateUI();
		jt.repaint();
		JOptionPane.showMessageDialog(this,"�޸ĳɹ�����",
                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//�����Ը���-����
		
	}
	public void selectManager(){
		if(jtxtArray[0].getText().equals("")){//��ʾ
			JOptionPane.showMessageDialog(this,"����ԱID����Ϊ�գ����������룡����",
			                              "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
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
    			
    			dtm.setDataVector(data,head);//����table����ʾ	
    			jt.updateUI();
    			jt.repaint();
    			return;	    	
	}	
		
		
		
		
		
		
		
    public void table(){
    	try{
		     int k=0;
			 Vector<Vector> vtemp = new Vector<Vector>();
			 
			 if(k==0){//��manager����û�иù���Ա�ţ��������ʾ�Ի���
			 	 JOptionPane.showMessageDialog(this,"û����Ҫ���ҵ�����",
				                 "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
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

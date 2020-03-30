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
		new JLabel("         ��    ��"),
		new JLabel("         ��    ��"),
		new JLabel("         ��    ��"),
		new JLabel("         �� �� ��"),
		new JLabel("         ��������"),
		new JLabel("         ISBN"),
		new JLabel("         ���")
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
	//����JButton��ť���ı�
	private JButton[] jbArray=
	{
	    new JButton("ͼ�����"),
	    new JButton("ɾ��ͼ��"),
	    new JButton("�޸�ͼ���¼"),
	    new JButton("����ͼ��"),
	    new JButton("ˢ��")
	};
	//��������
	Vector<String> head = new Vector<String>();
	{
		head.add("���");
		head.add("����");
		head.add("����");
		head.add("������");
		head.add("��������");
		head.add("ISBN");
		head.add("���");
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
			
			
			
	//�����Բ�ѯ-����			
	
	
	
	
	

	
	
	public admistaror_book_control()
	{
		this.data= first_data(this.data);
	    //�������ģ��
	    this.dtm=new DefaultTableModel(data,head);
	    //����Jtable����
	    this.jt=new JTable(dtm);
		//��JTable��װ����������
	    this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
		//���������ϲ���Ϊ�ղ��ֹ�����
		jpt.setLayout(null);
		//����jspt�зָ����ĳ�ʼλ��
		jsp.setDividerLocation(140);
		//���÷ָ����Ŀ��
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
		//�����²��Ӵ���
    	jsp.setBottomComponent(jspn);
    	jtxtArray[5].setBounds(595,10,100,20);
    	jtxtArray[6].setBounds(595,40,100,20);
		//��JButton��ӽ�jpt
		for(int i=0;i<5;i++)
		{
			jpt.add(jbArray[i]);
			jbArray[i].setBounds(150+112*i,100,112,25);
		}
		//���ü�����
		for(int i=0;i<5;i++)
		{
			jbArray[i].addActionListener(this);
		}		
		//���ô���Ĵ�Сλ�ü��ɼ���
		this.setBounds(5,5,600,500);
		this.setVisible(true);
	}
    public void actionPerformed(ActionEvent e){
    	//������꽹��
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
		if(e.getSource()==jbArray[0]){//���ͼ��
			this.insertBook();
		} 
	    if(e.getSource()==jbArray[1]){//�����Ϊ��ſ��������ɾ��
	    	this.deleteBook();
	    	}	
	    if(e.getSource()==jbArray[2]){//�����Ϊ��ſ������Ϣ�����޸�
	    	this.updateBook();
	    }	
	    if(e.getSource()==jbArray[3]){//��ѯͼ����Ϣ
	    	this.searchBook();
	    }
	    if(e.getSource()==jbArray[4]){//ˢ��ͼ����Ϣ
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
			dtm.setDataVector(data,head);//����table����ʾ	
			jt.updateUI();
			jt.repaint();
			JOptionPane.showMessageDialog(this," ˢ�³ɹ�����",
	                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
			
			
	    }
	}
    public void insertBook(){
		for(int i=0;i<7;i++){//�����������
            str1[i]=jtxtArray[i].getText().trim();		
		}
		if(str1[0].equals("")&&str1[1].equals("")&&str1[2].equals("")
			   &&str1[3].equals("")&&str1[4].equals("")&&str1[5].equals("")&&str1[6].equals("")){//������Ϊ�ս�����ʾ
			JOptionPane.showMessageDialog(this,	"ͼ����Ϣ����Ϊ�գ�����",
						        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
	            return;	
		}
	    if(!str1[0].equals("")&&!str1[1].equals("")&&!str1[2].equals("")
		   &&!str1[3].equals("")&&!str1[4].equals("")){//��ͼ����Ϣ����bookinfo��
			sql="insert into book_info values('"+str1[0]+"','"+str1[1]+"','"
			 + str1[2] + "',' "+str1[3]+"','"+
			            str1[4]+"','"+str1[5]+"',"+str1[6]+")";
//�����Բ���-��ʼ
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
			
			
			
			
			
			
			
			
			
//�����Բ���-����
	dtm.setDataVector(data,head);//����table����ʾ	
			jt.updateUI();
			jt.repaint();
			JOptionPane.showMessageDialog(this,	"����ɹ�",
			        "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
    }		
	public void deleteBook(){
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,1).toString();
		String sql1="select * from record where book_name='"+selected_row+"'";
		String sql2="delete from book_info where name='"+selected_row+"'";
		String sql3 ="delete from record where book_name='"+selected_row+"'";
		
		//������ɾ��-��ʼ
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
		dtm.setDataVector(data,head);//����table����ʾ	
		jt.updateUI();
		jt.repaint();
		JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//������ɾ��-����		
		
	}
	public void updateBook(){
		for(int i=0;i<7;i++){//�����������
            str1[i]=jtxtArray[i].getText().trim();		
		}
		int row = jt.getSelectedRow();
		String selected_row = jt.getValueAt(row,1).toString();
		String sql1="update book_info set number = '"+str1[0]+"', author = '"+str1[2]+"', press = '"+str1[3]+"', time = '"+str1[4]+"', ISBN = '"+str1[5]+"', quantity = '"+str1[6]+"'where name='"+selected_row+"'";
		
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
		dtm.setDataVector(data,head);//����table����ʾ	
		jt.updateUI();
		jt.repaint();
		JOptionPane.showMessageDialog(this,"�޸ĳɹ�����",
                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
		return;
		
		
		
		
		
//�������޸�-����
		
	}
	public void searchBook(){//������ʾ�Ի���
		JOptionPane.showMessageDialog(this,	"������ߴ��ڵ� '��ѯͼ��' ��ť����",
						        "ǿ���Ƽ�",JOptionPane.INFORMATION_MESSAGE);	
	    return;
	}
}

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
	//�����ָ��Ϊ���µ�JSplitePane����
    private JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jp2=new JPanel();
	//������ť����
	int flag;
	String sql;
	DataBase db;
	private JButton jb2=new JButton("ԤԼ");
	private JButton jb3=new JButton("ȷ��");
	private JButton jb4=new JButton("ˢ��");
	private JLabel jl3=new JLabel("������Ҫ��ѯ������");
	//��jsp1����ı���
	private JTextField jtxt3=new JTextField();
	private String student_id = null;
	Vector<String> head = new Vector<String>();	//��������
	{
		head.add("���");
		head.add("����");
		head.add("����");
		head.add("������");
		head.add("����ʱ��");
		head.add("ISBN");
		head.add("���");
	}
	//��jp3�����ñ��
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
    public student_book_borrow(String id)
    {
    	this.student_id = id;
    	this.data = first_data(this.data);
    	 //�������ģ��
        this.dtm=new DefaultTableModel(data,head);
        //����Jtable����
    	this.jt=new JTable(dtm);
    	//��JTable��װ����������
    	this.jspn=new JScrollPane(jt);
    	this.setLayout(new GridLayout(1,1));
    	//��jsp2���õ�jsp1���ϲ�����
    	jsp1.setTopComponent(jp2);
    	//����jsp1���²�����
    	jsp1.setBottomComponent(jspn);
    	//����jsp1��jsp2�зָ����ĳ�ʼλ��
    	jsp1.setDividerLocation(80);
    	//����jsp1��jsp2�зָ����Ŀ��
    	jsp1.setDividerSize(4);
    	//����jp1��jp2Ϊ�ղ��ֹ�����
    	jp2.setLayout(null);
    	//���ð�ť�Ĵ�С��λ��
		jb2.setBounds(500,30,95,20);
		jb3.setBounds(280,30,95,20);
		jb4.setBounds(720, 30, 95, 20);
    	//����ť��ӽ�JPanel
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		//����JLabel������
    	jl3.setBounds(30,30,120,20);
    	//��JLabel��ӽ�JPanel
    	jp2.add(jl3);
    	jtxt3.setBounds(155,30,100,20);
    	jp2.add(jtxt3);
    	this.add(jsp1);
    	//���ô���ı��⣬��Сλ�ü��ɼ���
        this.setBounds(10,10,800,600);
        this.setVisible(true);  
    }	
    //Ϊ�¼����صļ��������ϴ����¼�

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
				 JOptionPane.showMessageDialog(this,"���ѽ���/ԤԼ���飬�޷��ظ���������",
			                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
			 }
			 else
			 {
				 statement.execute("insert into record values('"+this.student_id+"','"+selected_row+"','"+jj+"','"+kk+"','��','��')");
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
				dtm.setDataVector(data,head);//����table����ʾ	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"���ĳɹ�����",
		                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
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
				 JOptionPane.showMessageDialog(this,"���ѽ���/ԤԼ���飬�޷��ظ���������",
			                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
			 }
			 else
			 {
				 statement.execute("insert into record values('"+this.student_id+"','"+selected_row+"','"+jj+"','"+kk+"','��','��')");
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
		JOptionPane.showMessageDialog(this,"ԤԼ�ɹ�����",
                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
		return;
	}
		}
		else if(e.getSource()==jb3)
		{
		
			if(jtxt3.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"��������Ϊ�գ���","��Ϣ",
                        JOptionPane.INFORMATION_MESSAGE);
                      return;
			}
			else
			{
				sql="select * from book_info where name='"+jtxt3.getText()+"'";
				
				//������ѡ��-��ʼ
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
			
//������ѡ��-����
				//�ӱ��м����ɹ��󣬰Ѳ鵽�����������Ϣ��ʾ�ڽ����²��ֵı���
				//Vector<Vector> vtemp = new Vector<Vector>();
							
				dtm.setDataVector(vtemp,head);	//����table	
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
					dtm.setDataVector(data,head);//����table����ʾ	
					jt.updateUI();
					jt.repaint();
					JOptionPane.showMessageDialog(this,"ˢ�³ɹ�����",
			                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
					return;
		}
		
	}}


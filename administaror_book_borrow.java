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
	//�����ָ��Ϊ���µ�JSplitePane����
	private String id = null;
    private JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	private JPanel jp2=new JPanel();
	//������ť����
	int flag;
	String sql;
	private JButton jb2=new JButton("ȷ��");
	private JButton jb3=new JButton("ˢ��");
	//��jp2���õ�ѡ��
	private JRadioButton[] jrbArray=
  {new JRadioButton("����ͼ��",true),new JRadioButton("ԤԼͼ��")};
  private ButtonGroup bg=new ButtonGroup();
	Vector<String> head = new Vector<String>();	//��������
	{
		head.add("���");
		head.add("����");
		head.add("����");
		head.add("������");
		head.add("����ʱ��");
		head.add("ISBN");
		head.add("������");
	}	
	Vector<Vector> data=new Vector<Vector>();//���������������    
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
	
	
	
	
	
	
	
	
	
  public administaror_book_borrow(String adm_id)
  {
	  this.id = adm_id;
	  this.data = first_data(this.data);
	  
	  this.dtm=new DefaultTableModel(this.data,head);//�������ģ��   
		this.jt=new JTable(dtm); //����Jtable����	
		this.jspn=new JScrollPane(jt);//��JTable�Ž���������

  	this.setLayout(new GridLayout(1,1));
  	//��jsp2���õ�jsp1���ϲ�����
  	jsp1.setTopComponent(jp2);
  	//����jsp1���²�����
  	jsp1.setBottomComponent(jspn);
  	//����jsp1��jsp2�зָ����ĳ�ʼλ��
  	jsp1.setDividerLocation(100);//���÷ָ�ؼ�λ��
  	jsp1.setDividerSize(4);//���÷ָ�ؼ����
  	jp2.setLayout(null);    	
		jb2.setBounds(380,20,100,20);//���ð�ť�Ĵ�С��λ��
		jb3.setBounds(530, 20, 100, 20);
  	//����ť��ӽ�JPanel
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
  	//���ô���ı��⣬��Сλ�ü��ɼ���
      this.setBounds(10,10,800,600);
      this.setVisible(true);  
  }	
  //Ϊ�¼����صļ��������ϴ����¼�
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
			dtm.setDataVector(data,head);//����table����ʾ	
			jt.updateUI();
			jt.repaint();
			JOptionPane.showMessageDialog(this," ˢ�³ɹ�����",
	                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(this,"�������޿�棡��",
					                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
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
		if(judge)//�жϸ����Ƿ��п��
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
					 JOptionPane.showMessageDialog(this,"���ѽ���/ԤԼ���飬�޷��ظ���������",
				                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','��','��')");
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
			JOptionPane.showMessageDialog(this,"�������޿�棡��",
	                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
			}//ѡ�н��ĵ�ѡ���������
			else//ѡ��ԤԼ��ѡ�������ʼ
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
					 JOptionPane.showMessageDialog(this,"���ѽ���/ԤԼ���飬�޷��ظ���������",
				                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','��','��')");
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
				 if(statement.executeQuery("select * from record where id = '"+this.id+"' and book_name = '"+selected_row+"'").next())
				 {
					 JOptionPane.showMessageDialog(this,"���ѽ���/ԤԼ���飬�޷��ظ���������",
				                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
						return;
				 }
				 else
				 {
					 statement.execute("insert into record values('"+this.id+"','"+selected_row+"','"+jj+"','"+kk+"','��','��')");
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
		}
		}
}

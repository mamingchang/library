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
	//�����ָ��Ϊ���µ�JSplitePane����
    private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
    //����JPanel����
	private JPanel jpt=new JPanel();
	private JPanel jpb=new JPanel();
	//������ť����
	private JButton[] jbArray=new JButton[]
	{
		new JButton	("��ʧ"),
		new JButton	("�黹"),
		new JButton	("ȷ��"),
		new JButton ("ˢ��")
	};
	private JLabel jl=new JLabel("��ѯ������:");

	private JTextField jtxt=new JTextField();
	private String administaror_id = null;

	//��������
	Vector<String> head = new Vector<String>();
	{
		head.add("ID");
		head.add("����");
		head.add("����ʱ��");
		head.add("����ʱ��");
		head.add("����");
		head.add("ԤԼ");
	}
	//
	Vector<Vector> data=new Vector<Vector>();
	//�����Բ�ѯ-��ʼ		
	
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
		
		
		
	//�����Բ�ѯ-����
	//�������ģ��
    
	public administaror_book_return(String new_id)
	{
		this.administaror_id = new_id;
		this.data = first_data(this.data,this.administaror_id);
		this.dtm=new DefaultTableModel(data,head);
	    //����Jtable����
		this.jt=new JTable(dtm);
		//��JTable��װ����������
		this.jspn=new JScrollPane(jt);
		this.setLayout(new GridLayout(1,1));
		//��������RetrunBook�������²��־�Ϊ�ղ��ֹ�����
		jpt.setLayout(null);
		jpb.setLayout(null);
		//����Label�Ĵ�С��λ��
           jl.setBounds(5,15,100,20);	
		//��Jlabel��ӵ�jpt�����
		 jpt.add(jl);
		//ΪJTextField���ô�С��λ��
		jtxt.setBounds(105,15,300,20);
    	//��JTextField��ӵ�jpt
	     jpt.add(jtxt);
        //����JBuuton�Ĵ�С��λ��
	    jbArray[0].setBounds(5,50,100,20);
        jbArray[1].setBounds(150,50,100,20);
        jbArray[2].setBounds(295,50,100,20);
        jbArray[3].setBounds(440, 50, 100, 20);
        //���JButton����������¼�������
         for(int i=0;i<4;i++)
		{
			 jpt.add(jbArray[i]);
			 jbArray[i].addActionListener(this);
		}

		//��jpt���õ�jsp���ϲ�����
    	jsp.setTopComponent(jpt);
        jsp.setBottomComponent(jspn);
    	jsp.setDividerSize(4);
    	this.add(jsp);
    	//����jsp�зָ����ĳ�ʼλ��
    	jsp.setDividerLocation(80);
		//���ô���Ĵ�Сλ�ü��ɼ���
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
			dtm.setDataVector(data,head);//����table����ʾ	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"ˢ�³ɹ�����",
		                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			
   		}
   		if(e.getSource()==jbArray[2]){//�¼�ԴΪ"ȷ��"��ť
   			if(jtxt.getText().trim().equals("")){//ѧ������Ϊ�գ���ʾ
   				JOptionPane.showMessageDialog(this,"������Ҫ��ѯ������",
					            "��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					return;
   			}
   			else{//����ѧ�Ž��в�ѯ
   				sql="select * from record where book_name="+jtxt.getText().trim();
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
				dtm.setDataVector(vtemp,head);	//����table	
				jt.updateUI();
				jt.repaint();
			}
   		}
   		if(e.getSource()==jbArray[1])
   		{//��Ҫ�黹ͼ��
   			long jj = (new Date().getTime())/86400000-17800;
   			//��¼��ǰʱ���������Ƿ���
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
   				if(ad.equals("��"))
   				{
   					judge = true;
   				}
   			}
   			else
   			{
   				JOptionPane.showMessageDialog(this,"δ�ҵ�����������","��Ϣ",
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
   			{//����ԤԼ��ͼ����в���ʱ

 //������ɾ��-��ʼ
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
   				dtm.setDataVector(data,head);//����table����ʾ	
   				jt.updateUI();
   				jt.repaint();
   				JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
   		                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
   				return;
   				
   				
   				
   				
   				
   		//������ɾ��-����
   		
   		
   		
   		
   		
   				
   			}
   			else
   			{//���Խ��ĵ�ͼ����в���ʱ
   				if((new Long(selected_return_time)).longValue()<jj)
   				{//�жϽ��ĵ�ͼ���Ƿ���,���ڲ���
   				long cha = jj-(new Long(selected_return_time)).longValue();
   				Vector<String>accept = null;
   				String gengxincaozuo = "update fine set money = money+"+cha+" where id = '"+this.administaror_id+"'";
   				//�����û��ķ����
   				String genggaiquanxian = "update administraor set permitted = '��'  where id = '"+this.administaror_id+"'";
   				//�����û���Ȩ��Ϊ��
   				{//�鿴�Ƿ�����ԤԼ���飬���У��򽫸����������,���򽫸���Ŀ����+1
   					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
		   	   			 statement = connect.createStatement();
		   	   			result = statement.executeQuery("select * from record where book_name = '"+selected_book_name+"' and ordered= '��' ");
//ע��˴���Ҫ���ĵĲ�ѯ�Ƿ��и߼�Ȩ�޵��û���ԤԼ����Ϣʱ
		   	   			if(result.next())
		   	   			{//��ѯ����ѧ��ԤԼ�Ȿ��,���Ȿ�������ѧ������ɾ���ü�¼
		   	   			long new_kk = (new Date().getTime())/86400000+30-17800;
						 long new_jj = (new Date().getTime())/86400000-17800;
		   	   				String change_id = result.getString(1);
		   	   				statement.execute("update record fine set ordered = '��',borrowed = '��',borrowtime = '"+new_jj+"',returntime = '"+new_kk+"'");
		   	   				
		   	   			}
		   	   			else
		   	   			{
		   	   				statement.execute("update book_info set quantity = quantity+1 where name = '"+selected_book_name+"'");
		   	   			}
		   	   			statement.execute(gengxincaozuo);
		   	   		statement.execute(genggaiquanxian);
		   	   		//ɾ���ü�¼
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
   					
   					dtm.setDataVector(data,head);//����table����ʾ	
   	   				jt.updateUI();
   	   				jt.repaint();
   	   				JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
   	   		                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
   	   				return;
   	   			 
   				}
   				}
   				else
   				//��Ƶ��黹���鱾ʱ��δ�����黹ʱ��ʱҪ���е�������
   				{
   	   				{//�鿴�Ƿ�����ԤԼ���飬���У��򽫸����������,���򽫸���Ŀ����+1
   	   					try {
   							Class.forName("com.mysql.cj.jdbc.Driver");
   							connect  = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=GMT", "root", "123");
   			   	   			 statement = connect.createStatement();
   			   	   			result = statement.executeQuery("select * from record where book_name = '"+selected_book_name+"' and ordered= '��' ");
//ע��˴���Ҫ���ĵĲ�ѯ�Ƿ��и߼�Ȩ�޵��û���ԤԼ����Ϣʱ
   			   	   			if(result.next())
   			   	   			{//��ѯ����ѧ��ԤԼ�Ȿ��,���Ȿ�������ѧ������ɾ���ü�¼
   			   	   			long new_kk = (new Date().getTime())/86400000+30-17800;
   							 long new_jj = (new Date().getTime())/86400000-17800;
   			   	   				String change_id = result.getString(1);
   			   	   				statement.execute("update record fine set ordered = '��',borrowed = '��',borrowtime = '"+new_jj+"',returntime = '"+new_kk+"'");
   			   	   				
   			   	   			}
   			   	   			else
   			   	   			{
   			   	   				statement.execute("update book_info set quantity = quantity+1 where name = '"+selected_book_name+"'");
   			   	   			}
   			   	   		//ɾ���ü�¼
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
   	   				dtm.setDataVector(data,head);//����table����ʾ	
   	   				jt.updateUI();
   	   				jt.repaint();
   	   				JOptionPane.showMessageDialog(this,"ɾ���ɹ�����",
   	   		                "��Ϣ!!",JOptionPane.INFORMATION_MESSAGE);
   	   				return;
   	   				
   	   				}
   				}
   			}
   			
   			
   			
   			
   			
   			
			
   		}
   		if(e.getSource()==jbArray[0])
   		{//��Ҫ��ʧͼ��
			int row = jt.getSelectedRow();
			String selected_id = jt.getValueAt(row,0).toString();
			String selected_book_name = jt.getValueAt(row,1).toString();
			String power = jt.getValueAt(row,5).toString();
			if(row<0)
			{
				JOptionPane.showMessageDialog(this,"��ѡ��Ҫ��ʧ����!!!","��Ϣ",
					                              JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else
			{
				if(power.equals("��"))
				{
					JOptionPane.showMessageDialog(this,"���ɶ�ԤԼ��ͼ����й�ʧ����!!!","��Ϣ",
                            JOptionPane.INFORMATION_MESSAGE);
                          return;
				}
				String sql1= "delete from record where id = '"+selected_id+"' and book_name = '"+selected_book_name+"'";
				String sql2="update fine set money = money+1000 where id = '"+this.administaror_id+"'";
				String sql3="update administraor set permitted where id = '"+this.administaror_id+"'";
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
//������ѡ��-����
				dtm.setDataVector(vtemp,head);	//����table	
				jt.updateUI();
				jt.repaint();
				JOptionPane.showMessageDialog(this,"��ʧ�ɹ�!!!","��Ϣ",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
				
				
				
			}
			
   		}
   	}
   	public static void main(String[] args)
   	{
   	}
}

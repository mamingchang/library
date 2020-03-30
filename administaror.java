package Library;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
public class administaror extends JFrame
{
	administaror_info admin = null;
	//�����ڵ�����
	DefaultMutableTreeNode[]  tree_nodes=
			{new DefaultMutableTreeNode("ͼ��ݹ���ϵͳ"),
			 new DefaultMutableTreeNode("ѧ���û�����"),
			 new DefaultMutableTreeNode("ͼ�����"),
			 new DefaultMutableTreeNode("��ѯͼ��"),
			 new DefaultMutableTreeNode("����ԤԼͼ��"),
			 new DefaultMutableTreeNode("�黹��ʧͼ��"),
			 new DefaultMutableTreeNode("���ɷ���"),
			 new DefaultMutableTreeNode("����Ա����"),
			 new DefaultMutableTreeNode("�˳�")};    
    DefaultTreeModel tree_model=new DefaultTreeModel(tree_nodes[0]);//������ģ�ͣ�ָ�����ڵ�Ϊ"ѧ������ϵͳ"    
    JTree tree=new JTree(tree_model);//����������ģ�͵�JTree����    
    JScrollPane tree_scroll=new JScrollPane(tree);//ΪJTree������������    
    private JSplitPane spilt_pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);//�����ָ�����  
    private JPanel pane=new JPanel();//����JPanel����       
	private JLabel jlRoot=new JLabel();
    private administaror_control adm_control;//��½����Ա��
    String administaror_id;//����ԱID
    String administaror_power;//����ԱȨ��
	CardLayout card_choose=new CardLayout();//��ȡ��Ƭ���ֹ���������
    public administaror(String adm_id,String power)
    {
    	this.administaror_id=adm_id;//��ù���ԱID
    	this.administaror_power = power;//��ȡ����ԱȨ��
    	adm_control=new administaror_control(adm_id);//��������Ա�������
   		//this.setManager();//���ù���ԱȨ��
       	this.initJp(adm_id);//��ʼ����Ƭ�������
    	this.addTreeListener();//Ϊ���ڵ�ע���¼�������
    	for(int i=1;i<9;i++)
    	{//����ڵ�����ӽڵ�    		
    		tree_model.insertNodeInto(tree_nodes[i],tree_nodes[0],i-1);	
    	}    	
		tree.setEditable(false);//���ø����нڵ��ǿɱ༭��		
		this.add(spilt_pane);//���������Ĺ���������ӽ�����		
		spilt_pane.setLeftComponent(tree);//���������Ĺ���������ӽ���ߵ��Ӵ���		
		pane.setBounds(200,50,600,500);//Ϊpane���ô�Сλ�ò���ӽ��ұߵ��Ӵ���
		spilt_pane.setRightComponent(pane);        
        spilt_pane.setDividerLocation(200);//���÷ָ����ĳ�ʼλ��        
        spilt_pane.setDividerSize(4);//���÷ָ����Ŀ��
        jlRoot.setFont(new Font("Courier",Font.PLAIN,30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		//���ô���Ĺرն��������⣬��С��λ�ü��ɼ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setTitle("ͼ�����ϵͳ-����Ա�û�-"+administaror_id);
		//���ô����״γ��ֵĴ�С��λ��--�Զ�����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=500;//��������
		int h=400;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//���ô����������Ļ����		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//����ȫ��
		this.setVisible(true);//���ô���ɼ�		
		tree.setShowsRootHandles(true);//������ʾ���ڵ�Ŀ���ͼ��
    }
 //   public void setManager()
//	{
//		String sql="select permitted from manager where mgNo='"+administaror_id+"'";
//		DataBase db=new DataBase();//�������ݿ������
//	}
	public void initJp(String adm_id)
	{
		pane.setLayout(card_choose);//���ò��ֹ�����Ϊ��Ƭ����
		pane.add(jlRoot,"root");//��Ӹ������ʾ��Ϣ
		if(this.administaror_power.equals("��"))
		{
			pane.add(new administaror_student_control(),"stu");//���ѧ������ģ�����
		}
		else
		{
			pane.add(jlRoot,"stu");//���ѧ������ģ�����
		}
		if(this.administaror_power.equals("��"))
		{
			pane.add(new admistaror_book_control(),"bc");//���ͼ�����ģ�����
		}
		else
		{
			pane.add(jlRoot,"stu");//���ѧ������ģ�����
		}
		
		pane.add(new administaror_book_select(),"bs");//��Ӳ���ͼ��������
		if(this.administaror_power.equals("��"))
		{
			pane.add(new administaror_book_borrow(adm_id),"bb");//��ӽ���ԤԼͼ��ģ�����
		}
		else
		{
			pane.add(this.jlRoot,"Manager");//��ӹ���Ա����ģ�����
		}
		
		pane.add(new administaror_book_return(this.administaror_id),"br");//��ӹ黹��ʧͼ�����
		if(this.administaror_power.equals("��"))
		{
			pane.add(this.adm_control,"Manager");//��ӹ���Ա����ģ�����
		}
		else
		{
			pane.add(this.jlRoot,"Manager");//��ӹ���Ա����ģ�����
		}
		pane.add(new administaror_fine(this.administaror_id),"fine");//��ӷ�������
	}
	public void addTreeListener()
	{
		tree.addTreeSelectionListener(new TreeSelectionListener()
			{
				public void valueChanged(TreeSelectionEvent e)
				{
					DefaultMutableTreeNode cdmtn=//�õ�ѡ�еĽڵ����
								(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
					String result=(String)cdmtn.getUserObject();//�õ��Զ���ڵ����
					if(result.equals("ͼ��ݹ���ϵͳ"))
					{//��ʾ�������Ϣ
						card_choose.show(pane,"root");
					}
	                if(result.equals("ѧ���û�����"))
					{//��ʾѧ���û��������	
	                	card_choose.show(pane,"stu");
					}
					else if(result.equals("ͼ�����"))
					{//��ʾͼ��������
						card_choose.show(pane,"bc");	
					}	
					if(result.equals("��ѯͼ��"))
					{//��ʾ��ѯͼ�����													
						card_choose.show(pane,"bs");
					}
					else if(result.equals("����ԤԼͼ��"))	
					{//��ʾ����ԤԼͼ�����
						card_choose.show(pane,"bb");
					}
					else if(result.equals("�黹��ʧͼ��"))	
					{//��ʾ�黹��ʧͼ�����
						card_choose.show(pane,"br");
					}
					else if(result.equals("���ɷ���"))	
					{//��ʾ���ɷ������
						card_choose.show(pane,"fine");
					}
					else if(result.equals("����Ա����"))	
					{//��ʾ����Ա�������
						card_choose.show(pane,"Manager");
					}
					else if(result.equals("�˳�"))
					{//��ʾ�˳�����
						int i=JOptionPane.showConfirmDialog(administaror.this,"�Ƿ��˳�ϵͳ?",
																"��Ϣ",JOptionPane.YES_NO_OPTION);
						if(i==JOptionPane.YES_OPTION)
						{//�˳�ϵͳ
							System.exit(0);
						}	
					}									
				}
			});
	}
    public static void main(String args[]){}
}
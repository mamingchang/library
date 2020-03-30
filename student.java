package Library;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.net.*;
public class student extends JFrame implements ActionListener{
	student_info stu = null;
	DefaultMutableTreeNode[] dmtn={//�����ڵ�����
		new DefaultMutableTreeNode("ѧ���û�����ϵͳ"),
	    new DefaultMutableTreeNode("��ѯͼ��"),
	    new DefaultMutableTreeNode("ԤԼͼ��"),
	    new DefaultMutableTreeNode("��ʧͼ��"),
	    new DefaultMutableTreeNode("���ɷ���"),
	    new DefaultMutableTreeNode("�˳�")
	};
    DefaultTreeModel dtm=new DefaultTreeModel(dmtn[0]);//������ģ�ͣ�ָ�����ڵ�Ϊ"ѧ������ϵͳ"
    JTree jt=new JTree(dtm);    //��������dtm��ģ�͵�JTree����
    JScrollPane jsp=new JScrollPane(jt);    //ΪJTree������������    
    private JSplitPane jsplr=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true); //�ָ��Ϊ���·���
    Image image=new ImageIcon("tsgl.jpg").getImage();
    ImageIcon ii = new ImageIcon(image);    
    private JLabel jlRoot=new JLabel(ii);//��ǩ�ı��Զ�����
    private JPanel jp=new JPanel();    //����JPanel����
    private String student_id = null;
    private String student_power = null;
	CardLayout cl=new CardLayout();	//��ȡ��Ƭ���ֹ���������
	public student(String new_id,String new_power){
		//try {Socket ss = new Socket("127.0.0.1",9999);}
		//catch(Exception ex) {}
		this.student_id = new_id;
		this.student_power = new_power;
       	this.initJp();
    	jt.addTreeSelectionListener(new TreeSelectionListener(){//���ڲ�����ʾ���ĸ�ѡ��ڵ�
			public void valueChanged(TreeSelectionEvent e){
				DefaultMutableTreeNode cdmtn=
					(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				String cnv=(String)cdmtn.getUserObject();	
				if(cnv.equals("ѧ���û�����ϵͳ")){
					cl.show(jp,"root");//��ʾ������
				}
				if(cnv.equals("��ѯͼ��")){													
					cl.show(jp,"bs");//��ʾ"��ѯͼ��"����
				}
				else if(cnv.equals("ԤԼͼ��")){
					cl.show(jp,"bb");//��ʾ"ԤԼͼ��"����
				}
				else if(cnv.equals("��ʧͼ��")){
					cl.show(jp,"br");//��ʾ"�黹��ʧͼ��"����
				}
				else if(cnv.equals("���ɷ���")){
					cl.show(jp,"fine");//��ʾ"���ɷ���ͼ��"����
				}
				else if(cnv.equals("�˳�")){//��ѡ��˵���ʾ�Ƿ��˳�ϵͳ
					int i=JOptionPane.showConfirmDialog(student.this,
									"�Ƿ��˳�ϵͳ?","��Ϣ",JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION){System.exit(0);}						
				}									
			}
		});
    	for(int i=1;i<dmtn.length;i++){//����ڵ�����ӽڵ�
    		dtm.insertNodeInto(dmtn[i],dmtn[0],i-1);	
    	}
		jt.setEditable(false);//���ø����нڵ��ǲ��ɱ༭��
		this.add(jsplr);//���������Ĺ���������ӽ�����
		jsplr.setLeftComponent(jt);//���������Ĺ���������ӽ���ߵ��Ӵ���
		//Ϊjp���ô�Сλ�ò���ӽ��ұߵ��Ӵ���
		jp.setBounds(200,50,300,400);
		jsplr.setRightComponent(jp);
        jsplr.setDividerLocation(150);//���÷ָ����ĳ�ʼλ��
        jsplr.setDividerSize(4); //���÷ָ����Ŀ��
        jlRoot.setFont(new Font("Courier",Font.PLAIN,30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		//���ô���Ĺرն��������⣬��С��λ�ü��ɼ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ѧ������ϵͳ-ѧ���û�-"+student_id);
		//���ô����״γ��ֵĴ�С��λ��--�Զ�����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=500;//��������
		int h=400;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//���ô����������Ļ����
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//����ȫ��
		this.setVisible(true);
		jt.setShowsRootHandles(true);//������ʾ���ڵ�Ŀ���ͼ��
    }
    public void initJp(){//�������õķ�����������
    	jp.setLayout(cl);
    	jp.add(jlRoot,"root");
    	jp.add(new student_book_select(),"bs");
    	if(this.student_power.equals("��"))
    	{
    		jp.add(new student_book_borrow(this.student_id),"bb");
    	}
    	else
    	{
    		jp.add(jlRoot,"bb");
    	}
    	
    	jp.add(new student_book_return(this.student_id),"br");
    	jp.add(new student_fine(this.student_id),"fine");
    }
    public void actionPerformed(ActionEvent e){}//ʵ��ActionListener����
	public static void main(String[]args){//new student();
	}
}
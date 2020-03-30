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
	DefaultMutableTreeNode[] dmtn={//创建节点数组
		new DefaultMutableTreeNode("学生用户管理系统"),
	    new DefaultMutableTreeNode("查询图书"),
	    new DefaultMutableTreeNode("预约图书"),
	    new DefaultMutableTreeNode("挂失图书"),
	    new DefaultMutableTreeNode("缴纳罚款"),
	    new DefaultMutableTreeNode("退出")
	};
    DefaultTreeModel dtm=new DefaultTreeModel(dmtn[0]);//创建树模型，指定根节点为"学生管理系统"
    JTree jt=new JTree(dtm);    //创建包含dtm树模型的JTree对象
    JScrollPane jsp=new JScrollPane(jt);    //为JTree创建滚动窗体    
    private JSplitPane jsplr=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true); //分割方向为上下方向
    Image image=new ImageIcon("tsgl.jpg").getImage();
    ImageIcon ii = new ImageIcon(image);    
    private JLabel jlRoot=new JLabel(ii);//标签文本自动居中
    private JPanel jp=new JPanel();    //创建JPanel对象
    private String student_id = null;
    private String student_power = null;
	CardLayout cl=new CardLayout();	//获取卡片布局管理器引用
	public student(String new_id,String new_power){
		//try {Socket ss = new Socket("127.0.0.1",9999);}
		//catch(Exception ex) {}
		this.student_id = new_id;
		this.student_power = new_power;
       	this.initJp();
    	jt.addTreeSelectionListener(new TreeSelectionListener(){//用内部类显示树的各选择节点
			public void valueChanged(TreeSelectionEvent e){
				DefaultMutableTreeNode cdmtn=
					(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				String cnv=(String)cdmtn.getUserObject();	
				if(cnv.equals("学生用户管理系统")){
					cl.show(jp,"root");//显示主界面
				}
				if(cnv.equals("查询图书")){													
					cl.show(jp,"bs");//显示"查询图书"界面
				}
				else if(cnv.equals("预约图书")){
					cl.show(jp,"bb");//显示"预约图书"界面
				}
				else if(cnv.equals("挂失图书")){
					cl.show(jp,"br");//显示"归还挂失图书"界面
				}
				else if(cnv.equals("缴纳罚款")){
					cl.show(jp,"fine");//显示"缴纳罚款图书"界面
				}
				else if(cnv.equals("退出")){//用选择菜单提示是否退出系统
					int i=JOptionPane.showConfirmDialog(student.this,
									"是否退出系统?","消息",JOptionPane.YES_NO_OPTION);
					if(i==JOptionPane.YES_OPTION){System.exit(0);}						
				}									
			}
		});
    	for(int i=1;i<dmtn.length;i++){//向根节点添加子节点
    		dtm.insertNodeInto(dmtn[i],dmtn[0],i-1);	
    	}
		jt.setEditable(false);//设置该树中节点是不可编辑的
		this.add(jsplr);//将包含树的滚动窗口添加进窗体
		jsplr.setLeftComponent(jt);//将包含树的滚动窗口添加进左边的子窗口
		//为jp设置大小位置并添加进右边的子窗口
		jp.setBounds(200,50,300,400);
		jsplr.setRightComponent(jp);
        jsplr.setDividerLocation(150);//设置分隔条的初始位置
        jsplr.setDividerSize(4); //设置分隔条的宽度
        jlRoot.setFont(new Font("Courier",Font.PLAIN,30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		//设置窗体的关闭动作，标题，大小，位置及可见性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("学生管理系统-学生用户-"+student_id);
		//设置窗体首次出现的大小和位置--自动居中
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=500;//本窗体宽度
		int h=400;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//设置窗体出现在屏幕中央
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗体全屏
		this.setVisible(true);
		jt.setShowsRootHandles(true);//设置显示根节点的控制图标
    }
    public void initJp(){//对所引用的方法进行声明
    	jp.setLayout(cl);
    	jp.add(jlRoot,"root");
    	jp.add(new student_book_select(),"bs");
    	if(this.student_power.equals("是"))
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
    public void actionPerformed(ActionEvent e){}//实现ActionListener方法
	public static void main(String[]args){//new student();
	}
}
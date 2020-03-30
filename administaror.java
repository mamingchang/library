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
	//创建节点数组
	DefaultMutableTreeNode[]  tree_nodes=
			{new DefaultMutableTreeNode("图书馆管理系统"),
			 new DefaultMutableTreeNode("学生用户管理"),
			 new DefaultMutableTreeNode("图书管理"),
			 new DefaultMutableTreeNode("查询图书"),
			 new DefaultMutableTreeNode("借阅预约图书"),
			 new DefaultMutableTreeNode("归还挂失图书"),
			 new DefaultMutableTreeNode("交纳罚款"),
			 new DefaultMutableTreeNode("管理员管理"),
			 new DefaultMutableTreeNode("退出")};    
    DefaultTreeModel tree_model=new DefaultTreeModel(tree_nodes[0]);//创建树模型，指定根节点为"学生管理系统"    
    JTree tree=new JTree(tree_model);//创建包含树模型的JTree对象    
    JScrollPane tree_scroll=new JScrollPane(tree);//为JTree创建滚动窗体    
    private JSplitPane spilt_pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);//创建分割窗体对象  
    private JPanel pane=new JPanel();//创建JPanel对象       
	private JLabel jlRoot=new JLabel();
    private administaror_control adm_control;//登陆管理员名
    String administaror_id;//管理员ID
    String administaror_power;//管理员权限
	CardLayout card_choose=new CardLayout();//获取卡片布局管理器引用
    public administaror(String adm_id,String power)
    {
    	this.administaror_id=adm_id;//获得管理员ID
    	this.administaror_power = power;//获取管理员权限
    	adm_control=new administaror_control(adm_id);//创建管理员管理面板
   		//this.setManager();//设置管理员权限
       	this.initJp(adm_id);//初始化卡片布局面板
    	this.addTreeListener();//为树节点注册事件监听器
    	for(int i=1;i<9;i++)
    	{//向根节点添加子节点    		
    		tree_model.insertNodeInto(tree_nodes[i],tree_nodes[0],i-1);	
    	}    	
		tree.setEditable(false);//设置该树中节点是可编辑的		
		this.add(spilt_pane);//将包含树的滚动窗口添加进窗体		
		spilt_pane.setLeftComponent(tree);//将包含树的滚动窗口添加进左边的子窗口		
		pane.setBounds(200,50,600,500);//为pane设置大小位置并添加进右边的子窗口
		spilt_pane.setRightComponent(pane);        
        spilt_pane.setDividerLocation(200);//设置分隔条的初始位置        
        spilt_pane.setDividerSize(4);//设置分隔条的宽度
        jlRoot.setFont(new Font("Courier",Font.PLAIN,30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		//设置窗体的关闭动作，标题，大小，位置及可见性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setTitle("图书管理系统-管理员用户-"+administaror_id);
		//设置窗体首次出现的大小和位置--自动居中
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=500;//本窗体宽度
		int h=400;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//设置窗体出现在屏幕中央		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗体全屏
		this.setVisible(true);//设置窗体可见		
		tree.setShowsRootHandles(true);//设置显示根节点的控制图标
    }
 //   public void setManager()
//	{
//		String sql="select permitted from manager where mgNo='"+administaror_id+"'";
//		DataBase db=new DataBase();//创建数据库类对象
//	}
	public void initJp(String adm_id)
	{
		pane.setLayout(card_choose);//设置布局管理器为卡片布局
		pane.add(jlRoot,"root");//添加根结点显示信息
		if(this.administaror_power.equals("是"))
		{
			pane.add(new administaror_student_control(),"stu");//添加学生管理模块界面
		}
		else
		{
			pane.add(jlRoot,"stu");//添加学生管理模块界面
		}
		if(this.administaror_power.equals("是"))
		{
			pane.add(new admistaror_book_control(),"bc");//添加图书管理模块界面
		}
		else
		{
			pane.add(jlRoot,"stu");//添加学生管理模块界面
		}
		
		pane.add(new administaror_book_select(),"bs");//添加查找图书管理界面
		if(this.administaror_power.equals("是"))
		{
			pane.add(new administaror_book_borrow(adm_id),"bb");//添加借阅预约图书模块界面
		}
		else
		{
			pane.add(this.jlRoot,"Manager");//添加管理员管理模块界面
		}
		
		pane.add(new administaror_book_return(this.administaror_id),"br");//添加归还挂失图书界面
		if(this.administaror_power.equals("是"))
		{
			pane.add(this.adm_control,"Manager");//添加管理员管理模块界面
		}
		else
		{
			pane.add(this.jlRoot,"Manager");//添加管理员管理模块界面
		}
		pane.add(new administaror_fine(this.administaror_id),"fine");//添加罚款处理界面
	}
	public void addTreeListener()
	{
		tree.addTreeSelectionListener(new TreeSelectionListener()
			{
				public void valueChanged(TreeSelectionEvent e)
				{
					DefaultMutableTreeNode cdmtn=//得到选中的节点对象
								(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
					String result=(String)cdmtn.getUserObject();//得到自定义节点对象
					if(result.equals("图书馆管理系统"))
					{//显示根结点信息
						card_choose.show(pane,"root");
					}
	                if(result.equals("学生用户管理"))
					{//显示学生用户管理界面	
	                	card_choose.show(pane,"stu");
					}
					else if(result.equals("图书管理"))
					{//显示图书管理界面
						card_choose.show(pane,"bc");	
					}	
					if(result.equals("查询图书"))
					{//显示查询图书界面													
						card_choose.show(pane,"bs");
					}
					else if(result.equals("借阅预约图书"))	
					{//显示借阅预约图书界面
						card_choose.show(pane,"bb");
					}
					else if(result.equals("归还挂失图书"))	
					{//显示归还挂失图书界面
						card_choose.show(pane,"br");
					}
					else if(result.equals("交纳罚款"))	
					{//显示缴纳罚款界面
						card_choose.show(pane,"fine");
					}
					else if(result.equals("管理员管理"))	
					{//显示管理员管理界面
						card_choose.show(pane,"Manager");
					}
					else if(result.equals("退出"))
					{//显示退出界面
						int i=JOptionPane.showConfirmDialog(administaror.this,"是否退出系统?",
																"消息",JOptionPane.YES_NO_OPTION);
						if(i==JOptionPane.YES_OPTION)
						{//退出系统
							System.exit(0);
						}	
					}									
				}
			});
	}
    public static void main(String args[]){}
}
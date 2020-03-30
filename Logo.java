package Library;
import javax.swing.*;
import java.lang.Runnable;
import java.awt.*;public class Logo extends JWindow implements Runnable 
{String filename; 
//Logo图像文件的文件名
public Logo(String name)
    {
	filename = name;
	}
public void run()
{
	ImageIcon ig = new ImageIcon(filename); 
	JButton btn = new JButton(ig); //将图片给JButton显示
	getContentPane().add(btn); //将显示图片的btn加到JPanel里
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize(); //获得屏幕的大小
	setLocation(screenSize.width/4, screenSize.height/4);//将Logo窗口显示在屏幕宽的1/4，高的1/4处
	setSize(ig.getIconWidth(), ig.getIconHeight()); //将Logo窗口大小设成图像的大小
	toFront(); //将Logo窗口显示为最前面的窗口
	setVisible(true); //显示该窗口
	
	}
public void setNotVisible()
{
	setVisible(false); //不显示该窗口
}
}

package Library;
import javax.swing.*;
import java.lang.Runnable;
import java.awt.*;public class Logo extends JWindow implements Runnable 
{String filename; 
//Logoͼ���ļ����ļ���
public Logo(String name)
    {
	filename = name;
	}
public void run()
{
	ImageIcon ig = new ImageIcon(filename); 
	JButton btn = new JButton(ig); //��ͼƬ��JButton��ʾ
	getContentPane().add(btn); //����ʾͼƬ��btn�ӵ�JPanel��
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize(); //�����Ļ�Ĵ�С
	setLocation(screenSize.width/4, screenSize.height/4);//��Logo������ʾ����Ļ���1/4���ߵ�1/4��
	setSize(ig.getIconWidth(), ig.getIconHeight()); //��Logo���ڴ�С���ͼ��Ĵ�С
	toFront(); //��Logo������ʾΪ��ǰ��Ĵ���
	setVisible(true); //��ʾ�ô���
	
	}
public void setNotVisible()
{
	setVisible(false); //����ʾ�ô���
}
}

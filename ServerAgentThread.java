package Library;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;

public class ServerAgentThread extends Thread
{
	Server father;//����Server������
	Socket sc;//����Socket������
	ObjectInputStream din;//���������������������������
	ObjectOutputStream dout;
	boolean flag=true;//�����̵߳ı�־λ
	public ServerAgentThread(Server father,Socket sc)
	{
		this.father=father;
		this.sc=sc;
		try
		{
			din=new ObjectInputStream(sc.getInputStream());//��������������
			dout=new ObjectOutputStream(sc.getOutputStream());//�������������
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(flag)
		{
			try
			{
				Object msg=din.readObject();//���տͻ��˴�������Ϣ
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void client_leave(String msg) throws IOException{
		try
		{
			System.out.println("safasfas");
			din.close();//�ر�����������
			dout.close();//�ر����������
			sc.close();//�ر�Socket
				this.flag=false;//��ֹ�÷����������߳�
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
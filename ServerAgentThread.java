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
	Server father;//声明Server的引用
	Socket sc;//声明Socket的引用
	ObjectInputStream din;//声明数据输入流与输出流的引用
	ObjectOutputStream dout;
	boolean flag=true;//控制线程的标志位
	public ServerAgentThread(Server father,Socket sc)
	{
		this.father=father;
		this.sc=sc;
		try
		{
			din=new ObjectInputStream(sc.getInputStream());//创建数据输入流
			dout=new ObjectOutputStream(sc.getOutputStream());//创建数据输出流
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
				Object msg=din.readObject();//接收客户端传来的信息
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
			din.close();//关闭数据输入流
			dout.close();//关闭数据输出流
			sc.close();//关闭Socket
				this.flag=false;//终止该服务器代理线程
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
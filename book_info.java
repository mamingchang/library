package Library;

import java.io.Serializable;

public class book_info implements Serializable {
	private String book_number = null;
	private String book_name = null;
	private String book_author = null;
	private String book_press = null;
	private String book_time = null;
	private String book_ISBN = null;
	private int book_quantity = 0;
	
	public book_info()
	{
		
	}
	public book_info(String name)
	{
		this.book_name = name;
	}
	public book_info(String number,String name,String author,String press,String time,String ISBN,int quantity)
	{
		set_book_number ( number);
		set_book_name ( name);
		set_book_author ( author);
		set_book_press ( press);
		set_book_time ( time);
		set_book_ISBN ( ISBN);
		set_book_quantity ( quantity);
	}
	public void set_book_number (String number)
	{
		this.book_number = number;
	}
	public void set_book_name (String name)
	{
		this.book_name = name;
	}
	public void set_book_author (String author)
	{
		this.book_author = author;
	}
	public void set_book_press (String press)
	{
		this.book_press = press;
	}
	public void set_book_time (String time)
	{
		this.book_time = time;
	}	public void set_book_ISBN (String ISBN)
	{
		this.book_ISBN = ISBN;
	}
	public void set_book_quantity (int quantity)
	{
		this.book_quantity = quantity;
	}
	public String get_book_name ()
	{
		return this.book_name ;
	}
	public String get_book_number ()
	{
		return this.book_number ;
	}
	public String get_book_author ()
	{
		return this.book_author ;
	}
	public String get_book_press ()
	{
		return this.book_press ;
	}
	public String get_book_time ()
	{
		return this.book_time ;
	}
	public String get_book_ISBN ()
	{
		return this.book_ISBN ;
	}
	public int get_book_quantity ()
	{
		return this.book_quantity ;
	}
}

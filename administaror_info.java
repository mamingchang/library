package Library;

import java.io.Serializable;

public class administaror_info implements Serializable {
	private String administaror_id;
	private String administaror_password;
public administaror_info (String id)
{
	this.administaror_id = id;
}
public administaror_info ()
{
	this.administaror_id = null;
	this.administaror_password = null;
}
public administaror_info (String id,String password)
{
	this.administaror_id = id;
	this.administaror_password = password;
}
public void set_administaror_id (String id)
{
	this.administaror_id  = id;
}
public void set_administaror_password (String password)
{
	this.administaror_password = password;
}
public String get_administaror_id ()
{
	return this.administaror_id ;
}
public String get_administaror_password ()
{
	return this.administaror_password ;
}
}

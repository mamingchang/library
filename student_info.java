package Library;

import java.io.Serializable;

public class student_info implements Serializable {
	private String student_id = null;
	private String student_name = null;
	private String student_sex = null;
	private String student_grade = null;
	private String student_class = null;
	private String student_department = null;
	private String student_password = null;
	private String student_tel = null;
public student_info()
{
	
}
public student_info(String id)
{
	this.student_id = id;
}
public student_info(String id,String name,String sex,String grade,String new_class,String department,String password,String tel)
{
	set_student_id (id);
	set_student_name ( name);
	set_student_sex ( sex);
	set_student_class ( new_class);
	set_student_grade ( grade);
	set_student_department ( department);
	set_student_tel ( tel);
	set_student_password ( password);
}
public void set_student_id (String id)
{
	this.student_id = id;
}
public void set_student_name (String name)
{
	this.student_name = name;
}
public void set_student_sex (String sex)
{
	this.student_sex = sex;
}
public void set_student_class (String new_class)
{
	this.student_class = new_class;
}
public void set_student_grade (String grade)
{
	this.student_grade = grade;
}
public void set_student_department (String department)
{
	this.student_department = department;
}
public void set_student_tel (String tel)
{
	this.student_tel = tel;
}
public void set_student_password (String password)
{
	this.student_password = password;
}
public String get_student_id ()
{
	return this.student_id ;
}
public String get_student_name ()
{
	return this.student_name ;
}
public String get_student_sex ()
{
	return this.student_sex ;
}
public String get_student_grade ()
{
	return this.student_grade ;
}
public String get_student_class ()
{
	return this.student_class ;
}
public String get_student_department ()
{
	return this.student_department ;
}
public String get_student_tel ()
{
	return this.student_tel ;
}
public String get_student_password ()
{
	return this.student_password ;
}
}

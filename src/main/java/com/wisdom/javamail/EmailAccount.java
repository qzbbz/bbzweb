package com.wisdom.javamail;

public class EmailAccount {
	
	private String m_smtp;
	private String m_from;
	private String m_user;
	private String m_pwd;
	
	public String getSmtp()
	{
		return m_smtp;
	};
	
	public void setSmtp(String smtp)
	{
		m_smtp = smtp;
	};
	
	public String getFrom()
	{
		return m_from;
	};
	
	public void setFrom(String from)
	{
		m_from = from;
	};
	
	public String getUser()
	{
		return m_user;
	};
	
	public void setUser(String user)
	{
		m_user = user;
	};
	
	public String getPwd()
	{
		return m_pwd;
	};
	
	public void setPwd(String pwd)
	{
		m_pwd = pwd;
	};
		
}
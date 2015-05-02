package com.wisdom.common.utils;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class JavaMail {
	private MimeMessage mimeMsg; // MIME邮件对象 
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private String username = ""; // smtp认证用户名和密码
	private String password = "";
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成//MimeMessage对象

	public JavaMail(String smtp)
	{
		setSmtpHost(smtp);
		createMimeMessage();
	}
	/**
	 * @param hostName
	 * String
	 */
	public void setSmtpHost(String hostName)
	{
		System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}
	/**
	 * @return boolean
	 *
	 */
	public boolean createMimeMessage()
	{
		try {
			System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		}catch (Exception e){
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}
		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart(); // mp 一个multipart对象
			// Multipart is a container that holds multiple body parts.
			return true;
		}catch (Exception e){
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}
	/**
	 * @param need
	 * boolean
	 *
	 */
	public void setNeedAuth(boolean need) {
		System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}
	/**
	 *
	 * @param name
	 * String
	 *
	 * @param pass
	 * String
	 *
	 */
	public void setNamePass(String name, String pass)
	{
		System.out.println("程序得到用户名与密码");
		username = name;
		password = pass;
	}
	/**
	 *
	 * @param mailSubject
	 * String
	 * @return boolean
	 *
	 */
	public boolean setSubject(String mailSubject) {
		System.out.println("设置邮件主题！"+mailSubject);
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		}
		catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}
	/**
	 *
	 * @param mailBody
	 * String
	 *
	 */
	public boolean setBody(String mailBody)
	{
		try{
			System.out.println("设置邮件体格式");
			BodyPart bp = new MimeBodyPart();
			bp.setContent(mailBody, "text/html;charset=utf-8");
			mp.addBodyPart(bp);
			return true;
		}catch (Exception e){
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}
	/**
	 *
	 * @param name
	 * String
	 * @param pass
	 * String
	 */
	public boolean addFileAffix(String filename) {
		System.out.println("增加邮件附件：" + filename);
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		}catch (Exception e){
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}
	/**
	 * @param name
	 * String
	 *
	 * @param pass
	 * String
	 */
	public boolean setFrom(String from) {
		System.out.println("设置发信人:" + from);
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * @param name
	 * String
	 *
	 * @param pass
	 * String
	 *
	 */
	public boolean setTo(String to)
	{
		boolean bRet = false;

		try{
			System.out.println("设置收信人"+to);
			//构建一个群发地址数组 
			String[] multiTo = to.split(";");
			InternetAddress[] adr=new InternetAddress[multiTo.length];  
			for(int i=0;i<multiTo.length;i++){ 
				adr[i]=new InternetAddress(multiTo[i]); 
			} 
			mimeMsg.setRecipients(Message.RecipientType.TO,adr);
			bRet = true;
		}catch (MessagingException e) {
			e.printStackTrace();
		} 
		return bRet;
	}
	/**
	 * @param name
	 * String
	 *
	 * @param pass
	 * String
	 */
	public boolean setCopyTo(String copyto)
	{
		boolean bRet = false;

		try {
			System.out.println("设置抄送人:"+copyto);
			//构建一个群发地址数组 
			String[] multiCopyTo = copyto.split(";");

			InternetAddress[] adr=new InternetAddress[multiCopyTo.length];  
			for(int i=0;i<multiCopyTo.length;i++){ 
				adr[i]=new InternetAddress(multiCopyTo[i]); 
				System.out.println(multiCopyTo[i]);
			} 
			mimeMsg.setRecipients(Message.RecipientType.CC,adr);
			bRet = true;
		}catch (MessagingException e) {
			e.printStackTrace();
		} 
		return bRet;

	}
	/**
	 * @param name
	 * String
	 *
	 * @param pass
	 * String
	 *
	 */
	public boolean sendout(StringBuffer strRet)
	{
		try{
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("连接smtp服务器:" + (String)props.get("mail.smtp.host") + " usr:" + username + " pwd:" + password);
			Session mailSession = Session.getInstance(props, null);
			System.out.println("正在发送邮件....");
			Transport transport = mailSession.getTransport("smtp"); 
			transport.connect((String) props.get("mail.smtp.host"), username, password);
			System.out.println("连接smtp服务器:" + (String)props.get("mail.smtp.host") + " usr:" + username + " pwd:" + password + " connect OK");
			if(null != mimeMsg.getRecipients(Message.RecipientType.TO)){
				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			}

			if(null != mimeMsg.getRecipients(Message.RecipientType.CC)){
				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			}

			if(null != mimeMsg.getRecipients(Message.RecipientType.BCC)){
				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.BCC));
			}
			System.out.println("发送邮件成功！");
			transport.close();
			return true;
		}catch (Exception e){
			strRet.append(e.toString());
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}
	public String readFileByLines(String fileName, String encode) 
	{
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader isr = new InputStreamReader(fis, encode);
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		System.out.println("content:"+sb.toString());
		return sb.toString();
	}


	/**
	 * Just do it as this
	 */
	public static void main(String[] args)
	{
		String mailbody = "http://www.laabc.com 用户邮件注册测试 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>";
		JavaMail themail = new JavaMail("smtp.163.com");
			themail.setNeedAuth(true);
			if (themail.setSubject("this is a test mail") == false)
				return;
			//邮件内容 支持html 如 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>
			if (themail.setBody(mailbody) == false)
				return;
			//收件人邮箱
			if (themail.setTo("273605907@qq.com;280491362@qq.com") == false)
				return;
			//发件人邮箱
			if (themail.setFrom("xuhaonupt@163.com") == false)
				return;
			themail.setNamePass("xuhaonupt@163.com", ""); // 用户名与密码
			StringBuffer strRet = new StringBuffer();
			if (themail.sendout(strRet) == false)
				return;
	}
}

package com.wisdom.dispatch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.utils.JavaMail;

@Service("javaMailService")
public class JavaMailService {
	@Autowired
	private JavaMail javaMail;
	private String username;
	private String password;
	
	public boolean sendMailOut(String to,String subject,String body,String from_user){
//		JavaMail javaMail = new JavaMail("smtp.jd.com");
		javaMail.setNamePass(username, password);
		javaMail.setNeedAuth(true);
		javaMail.setFrom(username);
		javaMail.setTo(to);
		javaMail.setSubject(subject);
		javaMail.setBody(body);
		StringBuffer strRet = new StringBuffer();
		boolean blRet = javaMail.sendout(strRet);
		return blRet;		
	}
}

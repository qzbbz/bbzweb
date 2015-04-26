package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserPwd entity. @author MyEclipse Persistence Tools
 */

public class UserPwd implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String pwd;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserPwd() {
	}

	/** minimal constructor */
	public UserPwd(Long id, String userId, String pwd) {
		this.id = id;
		this.userId = userId;
		this.pwd = pwd;
	}

	/** full constructor */
	public UserPwd(Long id, String userId, String pwd, Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.pwd = pwd;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
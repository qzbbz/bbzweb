package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserOpenid entity. @author MyEclipse Persistence Tools
 */

public class UserOpenid implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String openid;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserOpenid() {
	}

	/** minimal constructor */
	public UserOpenid(Long id, String userId, String openid) {
		this.id = id;
		this.userId = userId;
		this.openid = openid;
	}

	/** full constructor */
	public UserOpenid(Long id, String userId, String openid,
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.openid = openid;
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

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
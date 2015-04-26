package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserPhone entity. @author MyEclipse Persistence Tools
 */

public class UserPhone implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String phone;
	private Integer type;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserPhone() {
	}

	/** minimal constructor */
	public UserPhone(Long id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public UserPhone(Long id, String userId, String phone, Integer type,
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.phone = phone;
		this.type = type;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
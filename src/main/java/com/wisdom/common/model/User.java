package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String userName;
	private Integer typeId;
	private Long companyId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Long id, String userId, String userName, Integer typeId, Long companyId,
			Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.typeId = typeId;
		this.userName = userName;
		this.companyId = companyId;
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
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
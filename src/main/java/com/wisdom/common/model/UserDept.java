package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserDept entity. @author MyEclipse Persistence Tools
 */

public class UserDept implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private Long deptId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserDept() {
	}

	/** minimal constructor */
	public UserDept(Long id, String userId, Long deptId) {
		this.id = id;
		this.userId = userId;
		this.deptId = deptId;
	}

	/** full constructor */
	public UserDept(Long id, String userId, Long deptId, Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.deptId = deptId;
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

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
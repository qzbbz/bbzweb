package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Employment entity. @author MyEclipse Persistence Tools
 */

public class Employment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private String userId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Employment() {
	}

	/** minimal constructor */
	public Employment(Long id, Long companyId, String userId) {
		this.id = id;
		this.companyId = companyId;
		this.userId = userId;
	}

	/** full constructor */
	public Employment(Long id, Long companyId, String userId,
			Timestamp createTime) {
		this.id = id;
		this.companyId = companyId;
		this.userId = userId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * RolePrivate entity. @author MyEclipse Persistence Tools
 */

public class RolePrivate implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long companyId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public RolePrivate() {
	}

	/** minimal constructor */
	public RolePrivate(Long id, String name, Long companyId) {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
	}

	/** full constructor */
	public RolePrivate(Long id, String name, Long companyId,
			Timestamp createTime) {
		this.id = id;
		this.name = name;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
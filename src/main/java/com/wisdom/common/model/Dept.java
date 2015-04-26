package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long companyId;
	private Long parentId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** minimal constructor */
	public Dept(Long id, String name, Long companyId, Long parentId) {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
		this.parentId = parentId;
	}

	/** full constructor */
	public Dept(Long id, String name, Long companyId, Long parentId,
			Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.companyId = companyId;
		this.parentId = parentId;
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

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * AmountLimit entity. @author MyEclipse Persistence Tools
 */

public class AmountLimit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private Long deptId;
	private Long roleId;
	private Long limit;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public AmountLimit() {
	}

	/** minimal constructor */
	public AmountLimit(Long id, Long companyId, Long deptId, Long roleId,
			Long limit) {
		this.id = id;
		this.companyId = companyId;
		this.deptId = deptId;
		this.roleId = roleId;
		this.limit = limit;
	}

	/** full constructor */
	public AmountLimit(Long id, Long companyId, Long deptId, Long roleId,
			Long limit, Timestamp createTime) {
		this.id = id;
		this.companyId = companyId;
		this.deptId = deptId;
		this.roleId = roleId;
		this.limit = limit;
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

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getLimit() {
		return this.limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
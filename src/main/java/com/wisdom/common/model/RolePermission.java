package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * RolePermission entity. @author MyEclipse Persistence Tools
 */

public class RolePermission implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer objectTypeId;
	private Integer authActionId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public RolePermission() {
	}

	/** minimal constructor */
	public RolePermission(Long id, Integer objectTypeId, Integer authActionId) {
		this.id = id;
		this.objectTypeId = objectTypeId;
		this.authActionId = authActionId;
	}

	/** full constructor */
	public RolePermission(Long id, Integer objectTypeId, Integer authActionId,
			Timestamp createTime) {
		this.id = id;
		this.objectTypeId = objectTypeId;
		this.authActionId = authActionId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getObjectTypeId() {
		return this.objectTypeId;
	}

	public void setObjectTypeId(Integer objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public Integer getAuthActionId() {
		return this.authActionId;
	}

	public void setAuthActionId(Integer authActionId) {
		this.authActionId = authActionId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
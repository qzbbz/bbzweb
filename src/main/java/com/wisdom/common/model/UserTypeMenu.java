package com.wisdom.common.model;

import java.sql.Timestamp;

public class UserTypeMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userTypeId;
	private Long menuId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserTypeMenu() {
	}

	/** minimal constructor */
	public UserTypeMenu(Long id, Long userTypeId, Long menuId) {
		this.id = id;
		this.userTypeId = userTypeId;
		this.menuId = menuId;
	}

	/** full constructor */
	public UserTypeMenu(Long id, Long userTypeId, Long menuId,
			Timestamp createTime) {
		this.id = id;
		this.userTypeId = userTypeId;
		this.menuId = menuId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
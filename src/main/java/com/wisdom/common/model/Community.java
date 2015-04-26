package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Community entity. @author MyEclipse Persistence Tools
 */

public class Community implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private String data;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public Community() {
	}

	/** minimal constructor */
	public Community(Long id, String userId, String data) {
		this.id = id;
		this.userId = userId;
		this.data = data;
	}

	/** full constructor */
	public Community(Long id, String userId, String data, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.userId = userId;
		this.data = data;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
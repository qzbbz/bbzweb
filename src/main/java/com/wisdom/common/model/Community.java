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
	private Long tagId;
	private String title;
	private String abstractData;
	private String data;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public Community() {
	}

	/** full constructor */
	public Community(Long id, String userId, Long tagId, String title,
			String abstractData, String data, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.abstractData = abstractData;
		this.data = data;
		this.tagId = tagId;
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

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAbstractData() {
		return this.abstractData;
	}
	
	public void setAbstractData(String abstractData) {
		this.abstractData = abstractData;
	}
	
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getTagId() {
		return this.tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
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